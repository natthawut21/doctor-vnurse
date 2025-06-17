package doctor.booking

import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional
class AppointmentController {

    static responseFormats = ['json']
    static allowedMethods = [book: "POST", list: "GET"]

    AppointmentService appointmentService

    def book() {
        def body = request.JSON.asType(Map)
        def result = appointmentService.bookAppointment(body)

        if (result.status == 200) {
            render([message: result.message, id: result.id] as JSON)
        } else {
            render status: result.status, [error: result.error] as JSON
        }
    }

    def list() {
        def appointments = appointmentService.listAppointments()
        render appointments as JSON
    }
}