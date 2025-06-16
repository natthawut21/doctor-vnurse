package doctor.booking

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class DoctorScheduleControllerSpec extends Specification implements ControllerUnitTest<DoctorScheduleController> {

     void "test index action"() {
        when:
        controller.index()

        then:
        status == 200

     }
}
