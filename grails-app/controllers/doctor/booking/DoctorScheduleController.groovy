package doctor.booking

import grails.rest.RestfulController
import grails.converters.JSON
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

class DoctorScheduleController extends RestfulController<DoctorSchedule> {

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update : "PUT", listByDoctorId: "GET"]

    DoctorScheduleService doctorScheduleService

    DoctorScheduleController() {
        super(DoctorSchedule)
    }

    def save() {
        def result = doctorScheduleService.saveSchedule(request.JSON)
        if (result instanceof DoctorSchedule) {
            render result as JSON
        } else {
            render status: result.status ?: 400, result as JSON
        }
    }
    def update(Long id) {
        println("doctor-schedule-update -->${id}")
        def result = doctorScheduleService.updateScheduleById(id, request.JSON)
        if (result.status == 200) {
            render result.schedule as JSON
        } else {
            render status: result.status, result as JSON
        }
    }

    def listByDoctorId(Long id) {
        def result = doctorScheduleService.listSchedulesByDoctor(id)
        if (result.status == 404) {
            render status: 404, [error: result.message] as JSON
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
        def schedules = result.schedules
        schedules.sort { a, b ->
            def cmp = customOrder.indexOf(a.dayOfWeek) <=> customOrder.indexOf(b.dayOfWeek)
            return cmp == 0 ? a.startTime <=> b.startTime : cmp
        }
        def response = schedules.collect { schedule ->
            [
                id        : schedule.id,
                doctorId  : result.doctor.id,
                doctorName: result.doctor.name,
                dayOfWeek : schedule.dayOfWeek.toString(),
                startTime : schedule.startTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                endTime   : schedule.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            ]
        }
        render response as JSON
    }

    def deleteByDoctorAndSchedule() {
        def doctorId = params.long("doctorId")
        def scheduleId = params.long("scheduleId")
        def result = doctorScheduleService.deleteByDoctorAndSchedule(doctorId, scheduleId)
        render status: result.status, [message: result.message] as JSON
    }
}
