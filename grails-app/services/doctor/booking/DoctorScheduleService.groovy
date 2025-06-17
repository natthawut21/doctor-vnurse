package doctor.booking

import grails.gorm.transactions.Transactional
import java.time.DayOfWeek
import java.time.LocalTime

@Transactional
class DoctorScheduleService {

    def saveSchedule(Map requestData) {
        def doctor = Doctor.get(requestData.doctorId)
        if (!doctor) return [status: 404, message: "Doctor not found"]

        def newStart = LocalTime.parse(requestData.startTime)
        def newEnd = LocalTime.parse(requestData.endTime)
        def dayOfWeek = DayOfWeek.valueOf(requestData.dayOfWeek)

        def schedule = new DoctorSchedule(
            doctor: doctor,
            dayOfWeek: dayOfWeek,
            startTime: newStart,
            endTime: newEnd
        )
        schedule.save(flush: true) ? schedule : [status: 400, error: schedule.errors]
    }

    def listSchedulesByDoctor(Long doctorId) {
        def doctor = Doctor.get(doctorId)
        if (!doctor) return [status: 404, message: "Doctor not found"]
        def schedules = DoctorSchedule.findAllByDoctor(doctor)
        return [doctor: doctor, schedules: schedules]
    }

    def deleteByDoctorAndSchedule(Long doctorId, Long scheduleId) {
        def doctor = Doctor.get(doctorId)
        if (!doctor) return [status: 404, message: "Doctor not found"]

        def schedule = DoctorSchedule.findByIdAndDoctor(scheduleId, doctor)
        if (!schedule) return [status: 404, message: "Schedule not found for this doctor"]

        schedule.delete(flush: true)
        return [status: 200, message: "Schedule deleted successfully"]
    }
}
