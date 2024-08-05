package com.criar.pdi.demonstracao.config.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(new Info()
                        .title("API Demonstração PDI")
                        .description("API Rest da aplicação, contendo as funcionalidades de CRUD de controle de Lojas, Usuarios e Cliente, além de pagamentos e acompanhamento em tempo real")
                        .contact(new Contact()
                                .name("Contato")
                                .email("mario.marsn@gmail.com")));
    }
}
