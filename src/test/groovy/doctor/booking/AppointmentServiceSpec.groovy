package doctor.booking

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class AppointmentServiceSpec extends Specification implements ServiceUnitTest<AppointmentService> {

     void "test something"() {
        expect:
        service.doSomething()
     }
}
