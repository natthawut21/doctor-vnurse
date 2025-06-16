package doctor.booking

import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional
class AppointmentController {

    static responseFormats = ['json']
    static allowedMethods = [book: "POST", list: "GET"]

    def book() {
        def body = request.JSON

        println "[DEBUG] Request body: ${body}"

        def slot = AppointmentSlot.get(body.slotId)
        def doctor = Doctor.get(body.doctorId)
        def patient = User.findByUsername(body.username) // หรือจาก token auth

        println "[DEBUG] slot: ${slot}"
        println "[DEBUG] doctor: ${doctor}"
        println "[DEBUG] patient: ${patient}"

        if (!slot || !doctor || !patient) {
            render status: 400, text: "Invalid input"
            return
        }

        if (slot.booked) {
            render status: 409, text: "Slot already booked"
            return
        }

        slot.booked = true
        slot.save(flush: true)

        def appt = new Appointment(doctor: doctor, patient: patient, slot: slot)
        if (appt.save(flush: true)) {
            render([message: "Appointment booked", id: appt.id] as JSON)
        } else {
            render status: 500, [error: appt.errors] as JSON
        }
    }

    def list() {
        def appointments = Appointment.list()
        render appointments as JSON
    }
}
