package doctor.booking

import grails.rest.RestfulController
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class DoctorScheduleController extends RestfulController<DoctorSchedule> {

    static responseFormats = ['json']
    static allowedMethods = [save: "POST",listByDoctorId:"GET"]

    DoctorScheduleController() {
        super(DoctorSchedule)
    }




    @Transactional
    def save() {
        def requestData = request.JSON
        def doctor = Doctor.get(requestData.doctorId)
        println "doctorId param: ${requestData.doctorId}"
        print "${doctor}"

    if (!doctor) {
        render status: 404, text: "Doctor not found"
        return
    }

    
    def newStart = LocalTime.parse(requestData.startTime)
    def newEnd = LocalTime.parse(requestData.endTime)
    def dayOfWeek = DayOfWeek.valueOf(requestData.dayOfWeek)
    
    def existingSchedules = DoctorSchedule.findAllByDoctorAndDayOfWeek(doctor, dayOfWeek)

    def overlap = existingSchedules.any { s ->
        (newStart.isBefore(s.endTime) && newEnd.isAfter(s.startTime))
    }

    if (overlap) {
        render status: 400, text: "Time overlaps with an existing schedule"
        return
    }

    def schedule = new DoctorSchedule(
        doctor: doctor,
        dayOfWeek: dayOfWeek,
        startTime: newStart,
        endTime: newEnd
    )

    def response = new DoctorSchedule(
            doctorId  : doctor.id,
             doctorName: doctor.name,
        dayOfWeek : schedule.dayOfWeek.toString(),
        startTime : schedule.startTime,
        endTime   : schedule.endTime
    )
    
   

    if (schedule.save(flush: true)) {
        render response as JSON
    } else {
        render status: 400, [error: schedule.errors] as JSON
    }
    }

    @Transactional
    //def listByDoctor() 
    def listByDoctorId(Long id)
    {
        //def doctorId = params.long("doctorId")
        //def doctorId = params.doctorId?.toLong()
        def doctorId = id
                if (!doctorId) {
            render status: 400, text: "Missing doctorId parameter"
            return
        }

        def doctor = Doctor.get(doctorId)
        if (!doctor) {
            render status: 404, text: "Doctor not found"
            return
        }

         def customOrder = [
        DayOfWeek.SUNDAY,
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY
    ]

        // def schedules = DoctorSchedule.createCriteria().list {
        //     eq("doctor", doctor)
        //     order("dayOfWeek", "asc")
        //     order("startTime", "asc")
        // }
        def schedules = DoctorSchedule.findAllByDoctor(doctor)
        schedules.sort { a, b ->
        def cmp = customOrder.indexOf(a.dayOfWeek) <=> customOrder.indexOf(b.dayOfWeek)
        return cmp == 0 ? a.startTime <=> b.startTime : cmp
         }

        def response = schedules.collect { schedule ->
            [
                id        : schedule.id,
                doctorId  : doctor.id,
                doctorName: doctor.name,
                //dayOfWeek : schedule.dayOfWeek.toString(),
               // startTime : schedule.startTime.toString(),
               // endTime   : schedule.endTime.toString()
                dayOfWeek : schedule.dayOfWeek.toString(),
                startTime : schedule.startTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                endTime   : schedule.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            ]
        }

        render response as JSON

        
    }

    
    @Transactional
def deleteByDoctorAndSchedule() {
    def doctorId = params.long("doctorId")
    def scheduleId = params.long("scheduleId")

    if (!doctorId || !scheduleId) {
        render status: 400, text: "Missing doctorId or scheduleId"
        return
    }

    def doctor = Doctor.get(doctorId)
    if (!doctor) {
        render status: 404, text: "Doctor not found"
        return
    }

    def schedule = DoctorSchedule.findByIdAndDoctor(scheduleId, doctor)
    if (!schedule) {
        render status: 404, text: "Schedule not found for this doctor"
        return
    }

    schedule.delete(flush: true)
    render([message: "Schedule deleted successfully"] as JSON)
}
}
