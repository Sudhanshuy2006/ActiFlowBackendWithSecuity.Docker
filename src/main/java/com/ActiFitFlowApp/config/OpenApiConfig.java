package com.ActiFitFlowApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("ActiFitFlow APIs")
                        .version("1.0.0")
                        .description("Production-grade Spring Boot backend with JWT security, Dockerized deployment, scalable architecture, and AI-extensible analytics readiness")
                        .contact(new Contact()
                                .name("ActiFitFlow Team")
                                .email("support@actifitflow.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                        )
                );
    }
}
