package doctor.booking

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AppointmentSlotSpec extends Specification implements DomainUnitTest<AppointmentSlot> {

     void "test domain constraints"() {
        when:
        AppointmentSlot domain = new AppointmentSlot()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
