package doctor.booking

import grails.gorm.annotation.Entity

@Entity
class Doctor {
    String name
    String licenseNumber
    String specialty
    String phone
    String email
    static hasMany = [schedules: DoctorSchedule]
    
    static constraints = {
        name blank: false
        licenseNumber  unique: true, nullable: true
        specialty nullable: true
        phone nullable: true
        email nullable: true, email: true
    }
}