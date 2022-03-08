package com.github.alexduch.springboottraining.p2_boot;

import com.github.alexduch.springboottraining.core.Greeter;
import com.github.alexduch.springboottraining.core.GreetingProvider;
import com.github.alexduch.springboottraining.core.Hello;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("6 Utiliser les profils Spring Boot")
class GreetingProfileTest {

  @ParameterizedTest(name = "Avec le profil {0}")
  @CsvSource(
      value = {"test|Hey, this is a test!", "prod|Hello world!"},
      delimiter = '|')
  void testGreetingProfileSelection(String profile, String expectedGreeting) {
    new ApplicationContextRunner()
        .withUserConfiguration(GreetingProfileConfiguration.class)
        .withPropertyValues("spring.profiles.active=" + profile)
        .run(
            context ->
                assertThat(context)
                    .getBean(Greeter.class)
                    .extracting(greeter -> greeter.greet("world"))
                    .isEqualTo(expectedGreeting));
  }

  @Configuration
  @Import(Greeter.class)
  public static class GreetingProfileConfiguration {

    @Bean
    public GreetingProvider testGreetingProvider() {
      return () -> "Hey, this is a test!";
    }

    @Bean
    public GreetingProvider productionGreetingProvider() {
      return new Hello();
    }
  }
}
