package com.prueba.entrevista.c9c3c613304b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.prueba.entrevista.c9c3c613304b.entities.PriceEntity;
import com.prueba.entrevista.c9c3c613304b.repositories.PriceRepository;

@TestConfiguration(proxyBeanMethods = false)
public class TestPriceApplication {

	public static void main(String[] args) {
		SpringApplication.from(PriceApplication::main).with(TestPriceApplication.class).run(args);
	}

	private static final Logger log = LoggerFactory.getLogger(TestPriceApplication.class);

	@Bean
	CommandLineRunner initDatabase(PriceRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new PriceEntity()));
			log.info("Preloading " + repository.save(new PriceEntity()));
		};
	}

}
