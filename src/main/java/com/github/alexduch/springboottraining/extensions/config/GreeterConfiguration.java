package com.github.alexduch.springboottraining.extensions.config;

import com.github.alexduch.springboottraining.core.Bonjour;
import com.github.alexduch.springboottraining.core.Greeter;
import com.github.alexduch.springboottraining.core.GreetingProvider;
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
