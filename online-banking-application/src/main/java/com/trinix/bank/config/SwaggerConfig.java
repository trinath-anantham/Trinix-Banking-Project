package com.trinix.bank.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.trinix.bank.controller")).paths(regex("/accounts.*"))
				.build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Trinix Banking Project REST API", "Trinix REST API for Banking domain", "1.0",
				"Terms of service",
				new Contact("Trinath Anantham", "http://thespiritofjavaandweb.blogspot.in/",
						"trinath.teaching@gmail.com"),
				"Trinix License Version 1.0",
				"https://www.youtube.com/playlist?list=PLBBsYWqsnFzvtgJhgc35KEXh--4e_2Q-i");
		return apiInfo;
	}

}
