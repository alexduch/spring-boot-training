package com.github.alexduch.springboottraining.p1_spring.p12_java;

import com.github.alexduch.springboottraining.spring.Bonjour;
import com.github.alexduch.springboottraining.spring.Greeter;
import com.github.alexduch.springboottraining.spring.GreetingProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig
class GreeterTest {

  @Autowired(required = false)
  private Greeter greeter;

  @Test
  void name() {
    assertNotNull(greeter);
    assertEquals("Hello World!", greeter.greet("World"));
  }

  @Configuration
  private static class GreeterConfig {

    @Bean
    public GreetingProvider greetingProvider() {
      return new Bonjour();
    }
  }
}
