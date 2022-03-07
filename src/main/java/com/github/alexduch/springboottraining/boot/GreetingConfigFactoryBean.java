package com.github.alexduch.springboottraining.boot;

import org.springframework.beans.factory.FactoryBean;

public class GreetingConfigFactoryBean implements FactoryBean<GreetingConfigurationProperties> {

  private int numberOfCallsToGetObject = 0;

  @Override
  public GreetingConfigurationProperties getObject() {
    return new GreetingConfigurationProperties(
        numberOfCallsToGetObject++ % 2 == 0 ? "Hello, %s!" : "Hello again, %s!");
  }

  @Override
  public Class<?> getObjectType() {
    return String.class;
  }

  public int numberOfCallsToGetObject() {
    return numberOfCallsToGetObject;
  }
}
