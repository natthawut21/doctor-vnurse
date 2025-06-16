package doctor.booking

import grails.converters.JSON
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import javax.crypto.spec.SecretKeySpec
import java.util.Date
import grails.gorm.transactions.Transactional


class AuthController {

    static responseFormats = ['json']
    static allowedMethods = [register: "POST", login: "POST"]

    @Transactional
    def register() {
        def body = request.JSON
        
        def user = new User(body)
        if (user.save(flush: true)) {
            render([message: "Registered successfully"] as JSON)
        } else {
            render(status: 400, [error: user.errors] as JSON)
        }
    }

    def login() {
        def body = request.JSON
        def user = User.findByUsername(body.username)
        if (user && user.password == body.password) {
            def secretKey = "VNurseCareSuperSecretKey123456".bytes

            def token = Jwts.builder()
                .setSubject(user.username)
                .claim("role", user.role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 ชั่วโมง
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()

            render([token: token] as JSON)
        } else {
            render(status: 401, [error: "Invalid credentials"] as JSON)
        }
    }
}
