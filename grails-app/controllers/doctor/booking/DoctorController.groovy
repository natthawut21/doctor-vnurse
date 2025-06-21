package doctor.booking

import grails.rest.RestfulController
import grails.converters.JSON

import java.time.format.DateTimeFormatter

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
        def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        def response = [
            id: doctor.id,
            name: doctor.name,
            email: doctor.email,
            specialty: doctor.specialty,
            department: doctor.department,
            subSpecialty: doctor.subSpecialty,
            licenseNumber: doctor.licenseNumber,
            licenseIssuedDate: doctor.licenseIssuedDate ? doctor.licenseIssuedDate.format(formatter) : null,
            licenseExpiryDate: doctor.licenseExpiryDate ? doctor.licenseExpiryDate.format(formatter) : null,
            licenseIssuer: doctor.licenseIssuer,
            bankAccountNumber: doctor.bankAccountNumber,
            bankAccountName: doctor.bankAccountName

        ]

        render response as JSON
        //render doctor as JSON
    }

    def save() {
        def result = doctorService.saveDoctor(request.JSON)
        if (result instanceof Doctor) {
            def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            def response = [
                id: result.id,
                name: result.name,
                email: result.email,
                specialty: result.specialty,
                department: result.department,
                subSpecialty: result.subSpecialty,
                licenseNumber: result.licenseNumber,
                licenseIssuedDate: result.licenseIssuedDate ? result.licenseIssuedDate.format(formatter) : null,
                licenseExpiryDate: result.licenseExpiryDate ? result.licenseExpiryDate.format(formatter) : null,
                licenseIssuer: result.licenseIssuer,
                bankAccountNumber: result.bankAccountNumber,
                bankAccountName: result.bankAccountName
            ]
            render response as JSON
        } else {
            render status: 400, result as JSON
        }
    }

    def update(Long id) {
        def result = doctorService.updateDoctor(id, request.JSON)
        if (result == null) {
            render(status: 404, [error: "Doctor not found"] as JSON)
        } else if (result instanceof Doctor) {
             def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            def response = [
                id: result.id,
                name: result.name,
                email: result.email,
                specialty: result.specialty,
                department: result.department,
                subSpecialty: result.subSpecialty,
                licenseNumber: result.licenseNumber,
                licenseIssuedDate: result.licenseIssuedDate ? result.licenseIssuedDate.format(formatter) : null,
                licenseExpiryDate: result.licenseExpiryDate ? result.licenseExpiryDate.format(formatter) : null,
                licenseIssuer: result.licenseIssuer,
                bankAccountNumber: result.bankAccountNumber,
                bankAccountName: result.bankAccountName
            ]
            render response as JSON
            //render result as JSON
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