package com.github.alexduch.springboottraining.p1_spring;

import com.github.alexduch.springboottraining.spring.*;
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
class GreeterTest {

  @Nested
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
