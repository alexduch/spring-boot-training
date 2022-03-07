package com.github.alexduch.springboottraining.core;

public class Hello implements GreetingProvider {

  @Override
  public String greetingTemplate() {
    return "Hello %s!";
  }
}
