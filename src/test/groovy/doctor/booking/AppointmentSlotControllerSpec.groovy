package doctor.booking

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AppointmentSlotControllerSpec extends Specification implements ControllerUnitTest<AppointmentSlotController> {

     void "test index action"() {
        when:
        controller.index()

        then:
        status == 200

     }
}
