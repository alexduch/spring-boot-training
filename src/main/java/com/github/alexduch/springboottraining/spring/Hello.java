package com.github.alexduch.springboottraining.spring;

public class Hello implements GreetingProvider {

  @Override
  public String greetingTemplate() {
    return "Hello %s!";
  }
}
