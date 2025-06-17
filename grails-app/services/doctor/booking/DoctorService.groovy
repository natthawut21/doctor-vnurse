package doctor.booking

import grails.gorm.transactions.Transactional

@Transactional
class DoctorService {

    def listDoctors() {
        Doctor.list(sort: 'name', order: 'asc').collect { doctor ->
            [
                id       : doctor.id,
                name     : doctor.name,
                email    : doctor.email,
                specialty: doctor.specialty
            ]
        }
    }

    def getDoctor(Long id) {
        Doctor.get(id)
    }

    def saveDoctor(Map doctorData) {
        def doctor = new Doctor(doctorData)
        if (Doctor.findByNameAndEmail(doctor.name, doctor.email)) {
            return [error: "Name '${doctor.name}' and Email '${doctor.email}' already exists"]
        }
        doctor.save(flush: true) ? doctor : [error: doctor.errors]
    }

    def updateDoctor(Long id, Map doctorData) {
        def doctor = Doctor.get(id)
        if (!doctor) return null
        doctor.properties = doctorData
        doctor.save(flush: true) ? doctor : [error: doctor.errors]
    }

    def deleteDoctor(Long id) {
        def doctor = Doctor.get(id)
        if (!doctor) return null
        doctor.delete(flush: true)
        return true
    }
}