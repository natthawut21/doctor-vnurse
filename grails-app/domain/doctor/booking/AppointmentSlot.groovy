package doctor.booking

import java.time.LocalDateTime

class AppointmentSlot {
    Doctor doctor
    LocalDateTime startTime
    LocalDateTime endTime
    boolean booked = false

    static constraints = {
        doctor nullable: false
        startTime nullable: false
        endTime nullable: false
        booked nullable: false
    }
}
