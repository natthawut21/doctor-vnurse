package doctor.booking

import grails.converters.JSON

class AppointmentSlotController {

    static responseFormats = ['json']
    static allowedMethods = [generate: "POST"]

    AppointmentSlotService appointmentSlotService

    def generate() {
        def doctorId = params.long("doctorId")
        def date = params.date("date", "yyyy-MM-dd")
        def result = appointmentSlotService.generateSlots(doctorId, date)
        if (result.status != 200) {
            render status: result.status, [error: result.error] as JSON
        } else {
            render result.slots as JSON
        }
    }

    def showAll() {
        def doctorId = params.doctorId?.toLong()
        def dateStr = params.date
        def result = appointmentSlotService.showAllSlots(doctorId, dateStr)
        if (result.status != 200) {
            render status: result.status, [error: result.error] as JSON
        } else {
            render result.slots as JSON
        }
    }

    def available() {
        def doctorId = params.doctorId?.toLong()
        def dateStr = params.date
        def result = appointmentSlotService.availableSlots(doctorId, dateStr)
        if (result.status != 200) {
            render status: result.status, [error: result.error] as JSON
        } else {
            render result.slots as JSON
        }
    }
}