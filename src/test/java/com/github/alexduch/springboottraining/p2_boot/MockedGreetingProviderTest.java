package com.github.alexduch.springboottraining.p2_boot;

import com.github.alexduch.springboottraining.core.Greeter;
import com.github.alexduch.springboottraining.core.GreetingProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@DisplayName("7 Mocker un bean")
class MockedGreetingProviderTest {

  private GreetingProvider greetingProvider;

  @Test
  void testMockedGreetingProvider(@Autowired Greeter greeter) {
    when(greetingProvider.greetingTemplate()).thenReturn("Hi from a mock !");

    String greeting = greeter.greet("John");

    assertThat(greeting).isEqualTo("Hi from a mock !");
    verify(greetingProvider).greetingTemplate();
  }
}
