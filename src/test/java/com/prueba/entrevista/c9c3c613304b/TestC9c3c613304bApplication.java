package com.prueba.entrevista.c9c3c613304b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestC9c3c613304bApplication {

	public static void main(String[] args) {
		SpringApplication.from(C9c3c613304bApplication::main).with(TestC9c3c613304bApplication.class).run(args);
	}

}
