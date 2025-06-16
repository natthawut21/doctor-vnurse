package doctor.booking

import java.time.DayOfWeek
import java.time.LocalTime

class DoctorSchedule {

    Doctor doctor
    DayOfWeek dayOfWeek
    LocalTime startTime
    LocalTime endTime

    static constraints = {
        doctor nullable: false
        dayOfWeek nullable: false
        startTime nullable: false
        endTime nullable: false, validator: { val, obj ->
            return val.isAfter(obj.startTime)
        }
    }
}
