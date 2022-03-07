package com.github.alexduch.springboottraining.boot;

import com.github.alexduch.springboottraining.core.GreetingProvider;

public class ConfigurableGreetingProvider implements GreetingProvider {

  private final String template;

  public ConfigurableGreetingProvider(GreetingConfigurationProperties config) {
    this.template = config.template();
  }

  @Override
  public String greetingTemplate() {
    return template;
  }
}
