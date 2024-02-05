package com.yacer.unilearn.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "OpenApi Specification - Yacer Baba",
                contact = @Contact(
                        name = "Yacer",
                        email = "yacerbaba10@gmail.com"
                ),
                description = "OpenApi documentation for UniLearn",
                version = "1.0",
                termsOfService = "Terms of service"
        )
)
@SecurityScheme(
        name = "Jwt Authentication",
        description = "Add your JWT access token here",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
