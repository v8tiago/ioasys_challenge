package br.com.tiagocruz.ioasyschallenge.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

	@Bean
	public ModelMapper modelMapper(final List<AbstractConverter> converters) {

		final ModelMapper mapper = new ModelMapper();

		mapper.createTypeMap(String.class, LocalDate.class);
		mapper.createTypeMap(String.class, LocalDateTime.class);
		mapper.getConfiguration().setAmbiguityIgnored(true);

		converters.forEach(mapper::addConverter);

		return mapper;
	}
}
