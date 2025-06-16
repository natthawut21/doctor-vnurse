package doctor.booking

import grails.gorm.annotation.Entity

@Entity
class User {
    String username
    String password
    String role  // DOCTOR, PATIENT, ADMIN

    static constraints = {
        username blank: false, unique: true
        password blank: false
        role inList: ['DOCTOR', 'PATIENT', 'ADMIN']
    }
}
