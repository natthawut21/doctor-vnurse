package doctor.booking

import grails.gorm.transactions.Transactional
import java.time.format.DateTimeFormatter

@Transactional
class DoctorService {

    def listDoctors() {
        def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        Doctor.list(sort: 'name', order: 'asc').collect { doctor ->
            [
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