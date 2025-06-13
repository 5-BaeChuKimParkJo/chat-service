package com.chalnakchalnak.chatservice.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Chat-Service API",
                version = "v1",
                description = "Chat 서비스"
        ), security = {
        @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "Authorization")
}
)

// @Profile("!prod")
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        String[] paths = {"/api/v1/**"};
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch(paths)
                .build();
    }
}
