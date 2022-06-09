package com.jpct.simpleinterestapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleInterestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleInterestApiApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components())
                .info(new Info().title("Simple interest API")
                        .description("API microservice to calculate simple interest")
                        .version("1.0"));
    }
}
