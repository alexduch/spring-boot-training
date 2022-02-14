package com.github.alexduch.springboottraining.spring;

public class Bonjour implements GreetingProvider {
  @Override
  public String greetingTemplate() {
    return "Bonjour %s !";
  }
}
