package com.github.alexduch.springboottraining.extensions.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("greeting")
public record GreetingConfigurationProperties(String template) {
}
