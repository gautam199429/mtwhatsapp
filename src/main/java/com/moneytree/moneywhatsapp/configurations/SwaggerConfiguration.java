package com.moneytree.moneywhatsapp.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfiguration {

    @Bean
	OpenAPI custoOpenAPI(@Value("${spring.application.name}") String appName,
						 @Value("${spring.application.description}") String description,
						 @Value("${spring.application.version}") String version) {
		return new OpenAPI()
				.info(new Info().description(description).title(appName).version(version)
						.license(new License().name("Vartulz Technology Private Limited").url("https://vartulz.com"))
						.contact(new Contact().email("gautam@vartulz.com").name("Vartulz API Team")))
				.addServersItem(new Server().description("UAT").url("http://localhost:8070"))
				.addServersItem(new Server().description("PROD").url("https://whatsapp.moneytreerealty.com"));
	}
}
