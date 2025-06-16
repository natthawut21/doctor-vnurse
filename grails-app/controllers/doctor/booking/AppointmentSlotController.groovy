package doctor.booking

import grails.converters.JSON
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.*
import grails.gorm.transactions.Transactional
import java.time.format.DateTimeFormatter


class AppointmentSlotController {

    static responseFormats = ['json']
    static allowedMethods = [generate: "POST"]

    // @Transactional
    // def generate() {
    //     def doctorId = params.long("doctorId")
    //     def date = params.date("date", "yyyy-MM-dd")

    //     if (!doctorId || !date) {
    //         render status: 400, text: "Missing doctorId or date"
    //         return
    //     }

    //     def doctor = Doctor.get(doctorId)
    //     if (!doctor) {
    //         render status: 404, text: "Doctor not found"
    //         return
    //     }

        
    //     def localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

    //     def existingSlots = AppointmentSlot.createCriteria().list {
    //         eq("doctor", doctor)
    //         between("startTime",
    //                 LocalDateTime.of(localDate, LocalTime.MIN),
    //                 LocalDateTime.of(localDate, LocalTime.MAX)
    //         )
    //     }

    //     if (existingSlots && existingSlots.size() > 0) {
    //         render status: 409, text: "Slots already generated for this doctor on this date"
    //         return
    //     }

        
    //     def startTime = LocalDateTime.of(localDate, LocalTime.of(9, 0))
    //     def endTime = LocalDateTime.of(localDate, LocalTime.of(17, 0))
    //     def slots = []

      
    //     while (startTime.plusMinutes(15).isBefore(endTime)) {
    //         def slot = new AppointmentSlot(
    //             doctor: doctor,
    //             startTime: startTime,
    //             endTime: startTime.plusMinutes(15),
    //             booked: false
    //         )
    //         slot.save(flush: true)

    //         slots << slot
    //         startTime = startTime.plusMinutes(20) // 15 + 5 break
    //     }

    //     def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    //     def response = slots.collect { slot ->
    //     [
    //         id        : slot.id,
    //         doctorId  : slot.doctor?.id,
    //         startTime : slot.startTime.format(formatter),
    //         startTime_DayOfWeek: slot.startTime.dayOfWeek.toString(), 
    //         endTime   : slot.endTime.format(formatter),
    //         endTime_DayOfWeek: slot.endTime.dayOfWeek.toString(), 
    //         booked    : slot.booked
    //     ]
    // }
    //     render response as JSON
    // }

    // @Transactional
    // def generate() {
    //     def doctorId = params.long("doctorId")
    //     def date = params.date("date", "yyyy-MM-dd")

    //     if (!doctorId || !date) {
    //         render status: 400, text: "Missing doctorId or date"
    //         return
    //     }

    //     def doctor = Doctor.get(doctorId)
    //     if (!doctor) {
    //         render status: 404, text: "Doctor not found"
    //         return
    //     }

    //     def localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    //     def dayOfWeek = localDate.dayOfWeek.toString() // เช่น MONDAY, TUESDAY

    //     // 🔍 หา schedule ของหมอในวันนั้น
    //     def schedule = DoctorSchedule.findByDoctorAndDayOfWeek(doctor, dayOfWeek)
    //     if (!schedule) {
    //         render status: 400, text: "Doctor has no schedule on ${dayOfWeek}"
    //         return
    //     }

    //     // 🔁 เช็คว่า Slot ถูกสร้างไปแล้วหรือยัง
    //     def existingSlots = AppointmentSlot.createCriteria().list {
    //         eq("doctor", doctor)
    //         between("startTime",
    //                 LocalDateTime.of(localDate, LocalTime.MIN),
    //                 LocalDateTime.of(localDate, LocalTime.MAX)
    //         )
    //     }

    //     if (existingSlots && existingSlots.size() > 0) {
    //         render status: 409, text: "Slots already generated for this doctor on this date"
    //         return
    //     }

    //     // 🕘 ใช้เวลาจาก DoctorSchedule
    //     def startTime = LocalDateTime.of(localDate, schedule.startTime)
    //     def endTime = LocalDateTime.of(localDate, schedule.endTime)
    //     def slots = []

    //     while (startTime.plusMinutes(15).isBefore(endTime)) {
    //         def slot = new AppointmentSlot(
    //             doctor: doctor,
    //             startTime: startTime,
    //             endTime: startTime.plusMinutes(15),
    //             booked: false
    //         )
    //         slot.save(flush: true)

    //         slots << slot
    //         startTime = startTime.plusMinutes(20) // 15 + 5 break
    //     }

    //     def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    //     def response = slots.collect { slot ->
    //         [
    //             id        : slot.id,
    //             doctorId  : slot.doctor?.id,
    //             startTime : slot.startTime.format(formatter),
    //             startTime_DayOfWeek: slot.startTime.dayOfWeek.toString(),
    //             endTime   : slot.endTime.format(formatter),
    //             endTime_DayOfWeek: slot.endTime.dayOfWeek.toString(),
    //             booked    : slot.booked
    //         ]
    //     }

    //     render response as JSON
    // }
@Transactional
def generate() {
    def doctorId = params.long("doctorId")
    def date = params.date("date", "yyyy-MM-dd")

    if (!doctorId || !date) {
        render status: 400, text: "Missing doctorId or date"
        return
    }

    def doctor = Doctor.get(doctorId)
    if (!doctor) {
        render status: 404, text: "Doctor not found"
        return
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
        render status: 409, text: "Slots already generated for this doctor on this date"
        return
    }

    
    def schedules = DoctorSchedule.findAllByDoctorAndDayOfWeek(doctor, dayOfWeek)

    if (!schedules || schedules.isEmpty()) {
        render status: 400, text: "Doctor has no schedule on ${dayOfWeek}"
        return
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

    render allSlots as JSON
}



    @Transactional
    def showAll() {
        def doctorId = params.doctorId?.toLong()
        def dateStr = params.date

        if (!doctorId || !dateStr) {
            render status: 400, text: "Missing doctorId or date"
            return
        }

        def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        def date = LocalDate.parse(dateStr)
        
        def startOfDay = LocalDateTime.of(date, LocalTime.MIN)
        def endOfDay = LocalDateTime.of(date.plusDays(1), LocalTime.MIN)
        

        def slots = AppointmentSlot.createCriteria().list {
                eq("doctor", Doctor.get(doctorId))
                between("startTime", startOfDay, endOfDay)
                
        }

        render slots.collect {
            [
                id: it.id,
                startTime  : it.startTime.format(formatter),
                startTime_DayOfWeek: it.startTime.dayOfWeek.toString(), 
                endTime  : it.endTime.format(formatter),
                endTime_DayOfWeek: it.endTime.dayOfWeek.toString(), 
                doctorId: it.doctor.id,
                booked: it.booked,
                available: !it.booked
            ]
        } as JSON
    }
    @Transactional
    def available() {
        def doctorId = params.doctorId?.toLong()
        def dateStr = params.date

        if (!doctorId || !dateStr) {
            render status: 400, text: "Missing doctorId or date"
            return
        }

        def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        def date = LocalDate.parse(dateStr)
        
        def startOfDay = LocalDateTime.of(date, LocalTime.MIN)
        def endOfDay = LocalDateTime.of(date.plusDays(1), LocalTime.MIN)
        

        def slots = AppointmentSlot.createCriteria().list {
                eq("doctor", Doctor.get(doctorId))
                between("startTime", startOfDay, endOfDay)
                eq("booked", false)
        }

        render slots.collect {
            [
                id: it.id,
                //startTime: it.startTime,
                //endTime: it.endTime,
                startTime  : it.startTime.format(formatter),
                startTime_DayOfWeek: it.startTime.dayOfWeek.toString(), 
                endTime  : it.endTime.format(formatter),
                endTime_DayOfWeek: it.endTime.dayOfWeek.toString(), 
                doctorId: it.doctor.id,
                booked: it.booked,
                available: !it.booked
            ]
        } as JSON
    }
}
