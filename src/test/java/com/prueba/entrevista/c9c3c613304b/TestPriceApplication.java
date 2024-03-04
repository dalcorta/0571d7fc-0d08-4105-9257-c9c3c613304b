package com.prueba.entrevista.c9c3c613304b;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

@TestConfiguration(proxyBeanMethods = false)
@TestPropertySource("/test.properties")
public class TestPriceApplication {

	public static void main(String[] args) {
		SpringApplication.from(PriceApplication::main).with(TestPriceApplication.class).run(args);
	}

	@Bean
	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
		

		return hibernateProperties;
	}

}
