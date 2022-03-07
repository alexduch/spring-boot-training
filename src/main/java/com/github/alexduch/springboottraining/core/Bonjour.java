package com.github.alexduch.springboottraining.core;

public class Bonjour implements GreetingProvider {
  @Override
  public String greetingTemplate() {
    return "Bonjour %s !";
  }
}
