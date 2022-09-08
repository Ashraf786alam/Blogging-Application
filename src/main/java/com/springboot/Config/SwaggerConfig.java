package com.springboot.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKeys() {
		
		return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
		
	}
	
	private List<SecurityContext> securityContext(){
		
		return Arrays.asList(SecurityContext.builder().securityReferences(securityRefrences()).build());
	}
	
	private List<SecurityReference> securityRefrences(){
		
		AuthorizationScope scope=new AuthorizationScope("global","access everything");
		
		return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {scope}));
	}
	@Bean
	public Docket Api() {
		return new Docket(DocumentationType.SWAGGER_2).
				apiInfo(getInfo())
				.securityContexts(securityContext())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
				.apis(RequestHandlerSelectors.any()).
				paths(PathSelectors.any()).
				build();
	}

	private ApiInfo getInfo() {
		return new ApiInfo("Blogging Application:Backend Course","This Project is developed by Ashraf Alam","1.0", "Terms of service", "alamashraf356@gmail.com", "alamashraf356@gmail.com", "Api License URL");
		
		
	};

}
