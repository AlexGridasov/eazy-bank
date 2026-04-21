package com.gri.alex.cards;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"build.version=1", "spring.cloud.config.enabled=false"})
class CardApplicationTests {

	@Test
	void contextLoads() {
	}

}
