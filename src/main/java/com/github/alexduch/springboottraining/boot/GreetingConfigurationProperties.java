package com.github.alexduch.springboottraining.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("greeting")
public record GreetingConfigurationProperties(String template) {
}
