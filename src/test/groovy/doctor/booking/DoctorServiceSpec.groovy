package doctor.booking

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class DoctorServiceSpec extends Specification implements ServiceUnitTest<DoctorService> {

     void "test something"() {
        expect:
        service.doSomething()
     }
}
