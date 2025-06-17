package doctor.booking

import grails.rest.RestfulController
import grails.converters.JSON

class DoctorController {

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    DoctorService doctorService

    def index() {
        def doctors = doctorService.listDoctors()
        render doctors as JSON
    }

    def show(Long id) {
        def doctor = doctorService.getDoctor(id)
        if (!doctor) {
            render(status: 404, [error: "Doctor not found"] as JSON)
            return
        }
        render doctor as JSON
    }

    def save() {
        def result = doctorService.saveDoctor(request.JSON)
        if (result instanceof Doctor) {
            render result as JSON
        } else {
            render status: 400, result as JSON
        }
    }

    def update(Long id) {
        def result = doctorService.updateDoctor(id, request.JSON)
        if (result == null) {
            render(status: 404, [error: "Doctor not found"] as JSON)
        } else if (result instanceof Doctor) {
            render result as JSON
        } else {
            render status: 400, result as JSON
        }
    }

    def delete(Long id) {
        def deleted = doctorService.deleteDoctor(id)
        if (!deleted) {
            render(status: 404, [error: "Doctor not found"] as JSON)
            return
        }
        render([message: "Doctor deleted"] as JSON)
    }
}