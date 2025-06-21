package doctor.booking

import grails.gorm.transactions.Transactional
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.Date.*
import java.text.SimpleDateFormat

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

               def existingSlot = AppointmentSlot.createCriteria().list {
                                                eq("doctor", doctor)
                                                eq("startTime",startTime)
                                                eq("endTime",startTime.plusMinutes(15))
                                    }

                if (!existingSlot || existingSlot.size() == 0)
                {
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
                }
                else{
                    allSlots << existingSlot.collect {
                                [
                                    id: it.id,
                                    doctorId: it.doctor.id,
                                    startTime: it.startTime.format(formatter),
                                    startTime_DayOfWeek: it.startTime.dayOfWeek.toString(),
                                    endTime: it.endTime.format(formatter),
                                    endTime_DayOfWeek: it.endTime.dayOfWeek.toString(),
                                    booked: it.booked,
                                ]
                            }
                }

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
        def sdf = new SimpleDateFormat("yyyy-MM-dd")
        this.generateSlots(doctorId, sdf.parse(dateStr));

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
                        doctorId: it.doctor.id,
                        startTime: it.startTime.format(formatter),
                        startTime_DayOfWeek: it.startTime.dayOfWeek.toString(),
                        endTime: it.endTime.format(formatter),
                        endTime_DayOfWeek: it.endTime.dayOfWeek.toString(),
                        booked: it.booked,
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

        def sdf = new SimpleDateFormat("yyyy-MM-dd")
        this.generateSlots(doctorId, sdf.parse(dateStr));

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
                    doctorId: it.doctor.id,
                    startTime: it.startTime.format(formatter),
                    startTime_DayOfWeek: it.startTime.dayOfWeek.toString(),
                    endTime: it.endTime.format(formatter),
                    endTime_DayOfWeek: it.endTime.dayOfWeek.toString(),
                    booked: it.booked,
                    
                ]
            }
        ]
    }
}