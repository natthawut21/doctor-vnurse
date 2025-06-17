package doctor.booking

import grails.gorm.transactions.Transactional
import java.time.*
import java.time.format.DateTimeFormatter

@Transactional
class AppointmentSlotService {

    def generateSlots(Long doctorId, Date date) {
        if (!doctorId || !date) {
            return [status: 400, error: "Missing doctorId or date"]
        }
        def doctor = Doctor.get(doctorId)
        if (!doctor) {
            return [status: 404, error: "Doctor not found"]
        }
        def localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        def dayOfWeek = localDate.dayOfWeek.toString()
        def existingSlots = AppointmentSlot.createCriteria().list {
            eq("doctor", doctor)
            between("startTime",
                LocalDateTime.of(localDate, LocalTime.MIN),
                LocalDateTime.of(localDate, LocalTime.MAX)
            )
        }
        if (existingSlots && existingSlots.size() > 0) {
            return [status: 409, error: "Slots already generated for this doctor on this date"]
        }
        def schedules = DoctorSchedule.findAllByDoctorAndDayOfWeek(doctor, dayOfWeek)
        if (!schedules || schedules.isEmpty()) {
            return [status: 400, error: "Doctor has no schedule on ${dayOfWeek}"]
        }
        def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        def allSlots = []
        schedules.each { schedule ->
            def startTime = LocalDateTime.of(localDate, schedule.startTime)
            def endTime = LocalDateTime.of(localDate, schedule.endTime)
            while (startTime.plusMinutes(15).isBefore(endTime)) {
                def slot = new AppointmentSlot(
                    doctor: doctor,
                    startTime: startTime,
                    endTime: startTime.plusMinutes(15),
                    booked: false
                )
                slot.save(flush: true)
                allSlots << [
                    id       : slot.id,
                    doctorId : slot.doctor.id,
                    startTime: slot.startTime.format(formatter),
                    startTime_DayOfWeek: slot.startTime.dayOfWeek.toString(),
                    endTime  : slot.endTime.format(formatter),
                    endTime_DayOfWeek: slot.endTime.dayOfWeek.toString(),
                    booked   : slot.booked
                ]
                startTime = startTime.plusMinutes(20) // 15 + 5 break
            }
        }
        return [status: 200, slots: allSlots]
    }

    def showAllSlots(Long doctorId, String dateStr) {
        if (!doctorId || !dateStr) {
            return [status: 400, error: "Missing doctorId or date"]
        }
        def doctor = Doctor.get(doctorId)
        if (!doctor) {
            return [status: 404, error: "Doctor not found"]
        }
        def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        def date = LocalDate.parse(dateStr)
        def startOfDay = LocalDateTime.of(date, LocalTime.MIN)
        def endOfDay = LocalDateTime.of(date.plusDays(1), LocalTime.MIN)
        def slots = AppointmentSlot.createCriteria().list {
            eq("doctor", doctor)
            between("startTime", startOfDay, endOfDay)
        }
        return [
            status: 200,
            slots: slots.collect {
                [
                    id: it.id,
                    startTime: it.startTime.format(formatter),
                    startTime_DayOfWeek: it.startTime.dayOfWeek.toString(),
                    endTime: it.endTime.format(formatter),
                    endTime_DayOfWeek: it.endTime.dayOfWeek.toString(),
                    doctorId: it.doctor.id,
                    booked: it.booked,
                    available: !it.booked
                ]
            }
        ]
    }

    def availableSlots(Long doctorId, String dateStr) {
        if (!doctorId || !dateStr) {
            return [status: 400, error: "Missing doctorId or date"]
        }
        def doctor = Doctor.get(doctorId)
        if (!doctor) {
            return [status: 404, error: "Doctor not found"]
        }
        def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        def date = LocalDate.parse(dateStr)
        def startOfDay = LocalDateTime.of(date, LocalTime.MIN)
        def endOfDay = LocalDateTime.of(date.plusDays(1), LocalTime.MIN)
        def slots = AppointmentSlot.createCriteria().list {
            eq("doctor", doctor)
            between("startTime", startOfDay, endOfDay)
            eq("booked", false)
        }
        return [
            status: 200,
            slots: slots.collect {
                [
                    id: it.id,
                    startTime: it.startTime.format(formatter),
                    startTime_DayOfWeek: it.startTime.dayOfWeek.toString(),
                    endTime: it.endTime.format(formatter),
                    endTime_DayOfWeek: it.endTime.dayOfWeek.toString(),
                    doctorId: it.doctor.id,
                    booked: it.booked,
                    available: !it.booked
                ]
            }
        ]
    }
}