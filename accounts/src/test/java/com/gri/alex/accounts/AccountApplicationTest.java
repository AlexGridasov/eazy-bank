package com.gri.alex.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"build.version=1", "spring.cloud.config.enabled=false"})
class AccountApplicationTest {

  @Test
  void contextLoads() {
  }
}