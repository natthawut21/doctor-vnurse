package doctor.booking

import grails.rest.RestfulController
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class DoctorController {

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def doctors = Doctor.list(sort: 'name', order: 'asc').collect { doctor ->
        [
            id       : doctor.id,
            name     : doctor.name,
            email    : doctor.email,
            specialty: doctor.specialty
        ]
        }

        render doctors as JSON
    }
    @Transactional
    def show(Long id) {
        def doctor = Doctor.get(id)
        if (!doctor) {
            render(status: 404, [error: "Doctor not found"] as JSON)
            return
        }
        render doctor as JSON
    }
    @Transactional
    def save() {
        def doctor = new Doctor(request.JSON)
        if (Doctor.findByNameAndEmail(doctor.name,doctor.email)) {
            render status: 400, text: "Name '${doctor.name}' and Email '${doctor.email}' already exists"
        return
        }

        if (doctor.save(flush: true)) {
            render doctor as JSON
        } else {
            render(status: 400, [error: doctor.errors] as JSON)
        }
    }
    @Transactional
    def update(Long id) {
        def doctor = Doctor.get(id)
        if (!doctor) {
            render(status: 404, [error: "Doctor not found"] as JSON)
            return
        }
        

        doctor.properties = request.JSON
        if (doctor.save(flush: true)) {
            render doctor as JSON
        } else {
            render(status: 400, [error: doctor.errors] as JSON)
        }
    }
    @Transactional
    def delete(Long id) {
        def doctor = Doctor.get(id)
        if (!doctor) {
            render(status: 404, [error: "Doctor not found"] as JSON)
            return
        }
        doctor.delete(flush: true)
        render([message: "Doctor deleted"] as JSON)
    }
}