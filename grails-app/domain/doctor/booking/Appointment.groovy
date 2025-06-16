package doctor.booking

import grails.gorm.annotation.Entity

@Entity
class Appointment {
    Doctor doctor
    User patient
    AppointmentSlot slot
    Date createdAt = new Date()

    static constraints = {
        doctor nullable: false
        patient nullable: false
        slot nullable: false, unique: true
    }
}
