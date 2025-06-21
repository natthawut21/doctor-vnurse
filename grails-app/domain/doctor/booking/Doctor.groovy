package doctor.booking

import grails.gorm.annotation.Entity

import java.time.LocalDate

@Entity
class Doctor {
    String name
    String licenseNumber
    String specialty
    String subSpecialty     // ความถนัดเฉพาะทาง
    String department        // สาขาแพทย์
    String phone
    String email
    String bankAccountName  // บัญชีธนาคาร
    String bankAccountNumber // หมายเลขบัญชี
    String licenseIssuer     // หน่วยงานที่ออกใบอนุญาต
    LocalDate licenseIssuedDate     // วันที่ออกใบรับรอง
    LocalDate licenseExpiryDate     // วันหมดอายุใบรับรอง

    static hasMany = [schedules: DoctorSchedule]

    static constraints = {
        name blank: false
        licenseNumber  unique: true, nullable: true
        specialty nullable: true
        subSpecialty nullable: true
        department nullable: true
        phone nullable: true
        email nullable: true, email: true
        bankAccountName nullable: true
        bankAccountNumber nullable: true
        licenseIssuer nullable: true
        licenseIssuedDate nullable: true
        licenseExpiryDate nullable: true
        
    }
}