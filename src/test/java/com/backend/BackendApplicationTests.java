package com.backend;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.backend.mapper")
class BackendApplicationTests {

  @Test
  void contextLoads() {}
}
