package com.github.alexduch.springboottraining.spring;

import org.springframework.beans.factory.FactoryBean;

public class GreetingProviderFactoryBean implements FactoryBean<GreetingProvider> {

  @Override
  public GreetingProvider getObject() {
    return () -> "Ciao %s !";
  }

  @Override
  public Class<?> getObjectType() {
    return GreetingProvider.class;
  }
}
