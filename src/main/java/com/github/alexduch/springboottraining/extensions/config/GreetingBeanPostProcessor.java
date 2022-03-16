package com.github.alexduch.springboottraining.extensions.config;

import com.github.alexduch.springboottraining.core.GreetingProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class GreetingBeanPostProcessor implements BeanPostProcessor {

  private final List<String> processedBeanNames = new ArrayList<>();

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    processedBeanNames.add(beanName);
    if (bean instanceof GreetingProvider greetingProvider) {
      return (GreetingProvider)
          () -> "Here is the greeting: " + greetingProvider.greetingTemplate();
    }
    return bean;
  }

  public List<String> getProcessedBeanNames() {
    return Collections.unmodifiableList(processedBeanNames);
  }
}
