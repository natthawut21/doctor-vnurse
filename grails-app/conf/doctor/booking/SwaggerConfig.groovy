package doctor.booking

import io.swagger.v3.oas.models.*
import io.swagger.v3.oas.models.info.*
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.*

@Configuration
class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        new OpenAPI()
            .info(new Info()
                .title("Doctor Booking API")
                .version("1.0.0")
                .description("API documentation for Doctor Booking System")
            )
    }

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("doctor-booking")
                .pathsToMatch("/**")
                .build()
    }
}
