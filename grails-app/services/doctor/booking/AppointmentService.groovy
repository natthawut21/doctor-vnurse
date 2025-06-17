package doctor.booking

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject

@Transactional
class AppointmentService {

    def bookAppointment(Map body) {
        def slot = AppointmentSlot.get(body.slotId)
        def doctor = Doctor.get(body.doctorId)
        def patient = User.findByUsername(body.username) // หรือจาก token auth

        if (!slot || !doctor || !patient) {
            return [status: 400, error: "Invalid input"]
        }

        if (slot.booked) {
            return [status: 409, error: "Slot already booked"]
        }

        slot.booked = true
        slot.save(flush: true)

        def appt = new Appointment(doctor: doctor, patient: patient, slot: slot)
        if (appt.save(flush: true)) {
            return [status: 200, message: "Appointment booked", id: appt.id]
        } else {
            return [status: 500, error: appt.errors]
        }
    }

    def listAppointments() {
        Appointment.list()
    }
}