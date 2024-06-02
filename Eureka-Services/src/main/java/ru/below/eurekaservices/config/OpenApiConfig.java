package ru.below.eurekaservices.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(version = "1.0",
        description = "Eureka-Servises",
        contact = @Contact(name = "Viktor",
                email = "vibelow@mail.ru",
                url = "https://github.com/Belov-Viktor-Ivanovich/Zuzex_Test_Microservices/Eureka-Services"))

)

public class OpenApiConfig {
}
