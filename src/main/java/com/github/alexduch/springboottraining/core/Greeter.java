package com.github.alexduch.springboottraining.core;

public class Greeter {

  private final GreetingProvider greetingProvider;

  public Greeter(GreetingProvider greetingProvider) {
    this.greetingProvider = greetingProvider;
  }

  public String greet(String name) {
    return greetingProvider.greetingTemplate().formatted(name);
  }
}
