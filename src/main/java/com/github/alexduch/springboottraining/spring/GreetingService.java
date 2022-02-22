package com.github.alexduch.springboottraining.spring;

import org.springframework.transaction.annotation.Transactional;

public class GreetingService {

  private final Greeter greeter;

  public GreetingService(Greeter greeter) {
    this.greeter = greeter;
  }

  @Transactional
  public String greetTheWorld() {
    return greet("world");
  }

  public String greetJohn() {
    return greet("John");
  }

  @Transactional
  public String greet(String name) {
    return greeter.greet(name);
  }
}
