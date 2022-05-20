package com.waracle.cakemgr;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration(
		exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@SpringBootApplication
public class CakeMgrApplication {
	public static void main(String[] args) {
		SpringApplication.run(CakeMgrApplication.class, args);
	}

	// For mapping from the entity (jpa-data) classes to the domain classes
	// No real "mapping" required in this case, but included it anyway - layered design principle.
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

