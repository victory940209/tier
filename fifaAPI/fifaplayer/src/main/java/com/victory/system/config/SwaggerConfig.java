package com.victory.system.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Fifa API",
				description = "피파 API 선수정보",
				version = "v0.1",
				contact = @Contact(
						name = "victory",
						email = "victory940209@gmail.com"
				)
		)
)
@Configuration
public class SwaggerConfig {

	@Bean
	GroupedOpenApi group() {
		return GroupedOpenApi
				.builder()
				.group("Fifa Spid")
				.packagesToScan("com.victory")
				.build();
	}

}