package br.com.tiagocruz.ioasyschallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration("swaggerConfigProperties")
public class SwaggerConfigProperties {

	@Value("${swagger.enabled}")
	private boolean enabled;

	@Value("${api.version}")
	private String apiVersion;

	@Value("${swagger.title}")
	private String title;

	@Value("${swagger.description}")
	private String description;

	@Value("${swagger.useDefaultResponseMessages}")
	private boolean useDefaultResponseMessages;

	@Value("${swagger.enableUrlTemplating}")
	private boolean enableUrlTemplating;

	public boolean isEnabled() {

		return enabled;
	}

	public String getApiVersion() {

		return apiVersion;
	}

	public String getTitle() {

		return title;
	}

	public String getDescription() {

		return description;
	}

	public boolean isUseDefaultResponseMessages() {

		return useDefaultResponseMessages;
	}

	public boolean isEnableUrlTemplating() {

		return enableUrlTemplating;
	}

}