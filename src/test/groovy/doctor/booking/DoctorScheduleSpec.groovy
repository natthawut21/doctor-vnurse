package doctor.booking

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DoctorScheduleSpec extends Specification implements DomainUnitTest<DoctorSchedule> {

     void "test domain constraints"() {
        when:
        DoctorSchedule domain = new DoctorSchedule()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
