package com.github.alexduch.springboottraining.boot;

import com.github.alexduch.springboottraining.spring.Bonjour;
import com.github.alexduch.springboottraining.spring.Greeter;
import com.github.alexduch.springboottraining.spring.GreetingProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreeterConfiguration {

  @Bean
  public GreetingProvider greetingProvider() {
    return new Bonjour();
  }

  @Bean
  public Greeter greeter(GreetingProvider greetingProvider) {
    return new Greeter(greetingProvider);
  }
}
