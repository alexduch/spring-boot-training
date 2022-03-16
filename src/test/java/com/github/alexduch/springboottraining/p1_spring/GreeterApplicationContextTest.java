package com.github.alexduch.springboottraining.p1_spring;

import com.github.alexduch.springboottraining.core.Bonjour;
import com.github.alexduch.springboottraining.core.Greeter;
import com.github.alexduch.springboottraining.core.GreetingProvider;
import com.github.alexduch.springboottraining.core.GreetingService;
import com.github.alexduch.springboottraining.extensions.config.GreetingBeanPostProcessor;
import com.github.alexduch.springboottraining.extensions.config.GreetingConfigFactoryBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    public static class GreeterConfig {

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

    private final Greeter greeter;

    @Autowired
    FactoryBeanGreeterTest(Greeter greeter) {
      this.greeter = greeter;
    }

    @Test
    void testGreet() {
      assertNotNull(greeter);
      assertEquals("Ciao bello !", greeter.greet("bello"));
    }
  }

  @Nested
  @DisplayName("1.4 Mixte XML/Java/Scan")
  @ContextConfiguration(locations = "classpath:greeter-service.xml")
  class GreeterServiceTest {

    @Test
    void testGreet(@Autowired(required = false) GreetingService greeter) {
      assertNotNull(greeter);
      assertEquals("Ciao bello !", greeter.greet("bello"));
    }
  }

  @Nested
  @DisplayName("1.5 Avec le scope Prototype")
  @ContextConfiguration(locations = "classpath:greeter-prototype.xml")
  class PrototypeGreeterTest {

    @Autowired private GreetingProvider helloAgain;
    @Autowired private GreetingProvider hello;

    @Autowired private Greeter greeter;

    @Autowired private GreetingConfigFactoryBean greetingConfigFactoryBean;

    @Test
    void testGreet() {
      assertNotNull(greeter);
      assertEquals("Hello, World!", greeter.greet("World"));

      assertNotNull(hello);
      assertEquals("Hello, %s!", hello.greetingTemplate());

      assertNotNull(helloAgain);
      assertEquals("Hello again, %s!", helloAgain.greetingTemplate());

      assertNotSame(hello, helloAgain);
      assertEquals(3, greetingConfigFactoryBean.numberOfCallsToGetObject());
    }
  }

  @Nested
  @DisplayName("1.6 Avec un BeanPostProcessor")
  @ContextConfiguration
  class BeanPostProcessorGreeterTest {

    @Autowired private Greeter greeter;
    @Autowired private GreetingBeanPostProcessor postProcessor;

    @Test
    void testGreet() {
      assertNotNull(greeter);
      assertEquals("Here is the greeting: Hello world!", greeter.greet("world"));
      assertThat(postProcessor.getProcessedBeanNames())
          .hasSize(3)
          .containsExactlyInAnyOrder(
              "hello",
              "com.github.alexduch.springboottraining.core.Greeter",
              "com.github.alexduch.springboottraining.p1_spring.GreeterApplicationContextTest$BeanPostProcessorGreeterTest.ORIGINAL");
    }

    @Configuration
    @Import(Greeter.class)
    public static class PostProcessorConfiguration {

      @Bean
      public GreetingBeanPostProcessor postProcessor() {
        return new GreetingBeanPostProcessor();
      }

      @Bean
      public GreetingProvider bonjour() {
        return new Bonjour();
      }
    }
  }
}
