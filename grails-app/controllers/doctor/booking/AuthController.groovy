package doctor.booking

import grails.converters.JSON

class AuthController {

    static responseFormats = ['json']
    static allowedMethods = [register: "POST", login: "POST"]

    AuthService authService

    def register() {
        def body = request.JSON
        def result = authService.registerUser(body)
        if (result.status == 200) {
            render([message: result.message] as JSON)
        } else {
            render status: result.status, [error: result.error] as JSON
        }
    }

    def login() {
        def body = request.JSON
        def result = authService.loginUser(body)
        if (result.status == 200) {
            render([token: result.token] as JSON)
        } else {
            render status: result.status, [error: result.error] as JSON
        }
    }
}