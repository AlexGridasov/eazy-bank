package com.gri.alex.loans;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"build.version=1", "spring.cloud.config.enabled=false"})
class LoanApplicationTests {

	@Test
	void contextLoads() {
	}

}
