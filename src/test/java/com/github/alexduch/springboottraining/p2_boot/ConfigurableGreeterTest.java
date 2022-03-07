package com.github.alexduch.springboottraining.p2_boot;

import com.github.alexduch.springboottraining.core.Greeter;
import com.github.alexduch.springboottraining.extensions.config.GreeterConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("3 Utiliser @ConfigurationProperties")
class ConfigurableGreeterTest {

  @Test
  void testConfigurableGreeter() {
    new ApplicationContextRunner()
        .withPropertyValues("greeting.template=Hi %s!")
        .withUserConfiguration(GreeterConfiguration.class)
        .run(
            context ->
                assertThat(context)
                    .hasSingleBean(Greeter.class)
                    .getBean(Greeter.class)
                    .extracting(greeter -> greeter.greet("there"))
                    .isEqualTo("Hi there!"));
  }
}
