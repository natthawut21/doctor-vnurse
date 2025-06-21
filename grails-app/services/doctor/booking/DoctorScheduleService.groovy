package doctor.booking

import grails.gorm.transactions.Transactional
import java.time.DayOfWeek
import java.time.LocalTime

@Transactional
class DoctorScheduleService {

    def saveSchedule(Map requestData) {

        def doctor = Doctor.get(requestData.doctorId)
        if (!doctor) return [status: 404, message: "Doctor not found"]

        try {
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
        }catch (IllegalArgumentException e) {
            return [status: 400, message: "Invalid dayOfWeek value: ${requestData.dayOfWeek}. Must be one of: ${DayOfWeek.values()*.name()}"]
        }
    }

    def updateScheduleById(Long scheduleId, Map requestData) {
        def schedule = DoctorSchedule.get(scheduleId)
        if (!schedule) return [status: 404, message: "Schedule not found"]

        def oldDayOfWeek = schedule.dayOfWeek
        def doctor = schedule.doctor
        boolean timeChanged = requestData.startTime || requestData.endTime || requestData.dayOfWeek

         if (timeChanged) {
            // ลบ AppointmentSlot เดิมที่เกี่ยวข้อง
            def slotsToDelete = AppointmentSlot.createCriteria().list {
                eq("doctor", doctor)
                eq("booked", false)
                sqlRestriction("DAYOFWEEK(start_time) = ?", [oldDayOfWeek.getValue() + 1])
            }
        slotsToDelete.each { it.delete(flush: true) }
        }

        try {
            if (requestData.startTime) {
                schedule.startTime = LocalTime.parse(requestData.startTime)
            }
            if (requestData.endTime) {
                schedule.endTime = LocalTime.parse(requestData.endTime)
            }
            if (requestData.dayOfWeek) {
                schedule.dayOfWeek = DayOfWeek.valueOf(requestData.dayOfWeek)
            }
            return schedule.save(flush: true) ? [status: 200, schedule: schedule] : [status: 400, error: schedule.errors]
        }catch (IllegalArgumentException e) {
            return [status: 400, message: "Invalid dayOfWeek value: ${requestData.dayOfWeek}. Must be one of: ${DayOfWeek.values()*.name()}"]
        }
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
