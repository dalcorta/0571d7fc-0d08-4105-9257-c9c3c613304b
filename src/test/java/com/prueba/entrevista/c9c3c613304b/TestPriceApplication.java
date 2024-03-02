package com.prueba.entrevista.c9c3c613304b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestPriceApplication {

	public static void main(String[] args) {
		SpringApplication.from(PriceApplication::main).with(TestPriceApplication.class).run(args);
	}

}
