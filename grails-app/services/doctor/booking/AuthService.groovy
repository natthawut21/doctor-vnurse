package doctor.booking

import grails.gorm.transactions.Transactional
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

@Transactional
class AuthService {

    def registerUser(Map body) {
        def user = new User(body)
        if (user.save(flush: true)) {
            return [status: 200, message: "Registered successfully"]
        } else {
            return [status: 400, error: user.errors]
        }
    }

    def loginUser(Map body) {
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

            return [status: 200, token: token]
        } else {
            return [status: 401, error: "Invalid credentials"]
        }
    }
}