package com.github.alexduch.springboottraining.p1_spring;

import com.github.alexduch.springboottraining.spring.Bonjour;
import com.github.alexduch.springboottraining.spring.Greeter;
import com.github.alexduch.springboottraining.spring.GreetingProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DisplayName("1 Déboguer un ApplicationContext")
class GreeterApplicationContextTest {

  @Nested
  @DisplayName("1.1 Défini en XML")
  @ContextConfiguration(locations = "classpath:greeter.xml")
  class XmlContextGreeterTest {

    @Autowired(required = false)
    private Greeter greeter;

    @Test
    void testGreet() {
      assertNotNull(greeter);
      assertEquals("Hello World!", greeter.greet("World"));
    }
  }

  @Nested
  @DisplayName("1.2 Défini en Java")
  @ContextConfiguration
  class JavaContextGreeterTest {

    @Autowired(required = false)
    private Greeter greeter;

    @Test
    void testGreet() {
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

  @Nested
  @DisplayName("1.3 Contenant une FactoryBean")
  @ContextConfiguration(locations = "classpath:greeter-factory-bean.xml")
  class FactoryBeanGreeterTest {

    @Autowired(required = false)
    private Greeter greeter;

    @Test
    void testGreet() {
      assertNotNull(greeter);
      assertEquals("Ciao bello !", greeter.greet("bello"));
    }
  }
}
