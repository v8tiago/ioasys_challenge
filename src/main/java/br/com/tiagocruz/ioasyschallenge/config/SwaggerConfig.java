package br.com.tiagocruz.ioasyschallenge.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket ioasysApi(final SwaggerConfigProperties swaggerConfigProperties) {

		System.out.println(swaggerConfigProperties.isEnabled());
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo(swaggerConfigProperties))
				.enable(swaggerConfigProperties.isEnabled())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.any())
				.build()
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(swaggerConfigProperties.isUseDefaultResponseMessages())
				.enableUrlTemplating(swaggerConfigProperties.isEnableUrlTemplating());

	}

	private ApiInfo apiInfo(final SwaggerConfigProperties swaggerConfigProperties) {

		return new ApiInfoBuilder()
				.title(swaggerConfigProperties.getTitle())
				.description(swaggerConfigProperties.getDescription())
				.version(swaggerConfigProperties.getApiVersion())
				.build();
	}
}