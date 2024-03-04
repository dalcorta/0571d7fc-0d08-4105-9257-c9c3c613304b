package com.prueba.entrevista.c9c3c613304b;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest
@Sql(scripts = { "/create_schema.sql", "/brand.sql", "/product.sql",
		"/price.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
@Import(TestPriceApplication.class)
class PriceApplicationIT {

	@Test
	void contextLoads() {
	}

}
