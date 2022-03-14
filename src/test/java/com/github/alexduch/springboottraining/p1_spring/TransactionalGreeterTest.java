package com.github.alexduch.springboottraining.p1_spring;

import com.github.alexduch.springboottraining.core.Greeter;
import com.github.alexduch.springboottraining.core.GreetingService;
import com.github.alexduch.springboottraining.core.Hello;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.SimpleTransactionStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@DisplayName("2 Déboguer le déclenchement des transactions")
class TransactionalGreeterTest {

  @MockBean private PlatformTransactionManager transactionManager;

  @Autowired private GreetingService greetingService;

  @Test
  @DisplayName("2.1 Dans un appel interne depuis une méthode transactionnelle")
  void testGreetTheWorld() {
    when(transactionManager.getTransaction(any())).thenReturn(new SimpleTransactionStatus());

    assertEquals("Hello world!", greetingService.greetTheWorld());

    verify(transactionManager, times(2)).getTransaction(any());
    verify(transactionManager, times(2)).commit(any());
  }

  @Test
  @DisplayName("2.2 Dans un appel interne depuis une méthode non transactionnelle")
  void testGreetJohn() {
    when(transactionManager.getTransaction(any())).thenReturn(new SimpleTransactionStatus());

    assertEquals("Hello John!", greetingService.greetJohn());

    verify(transactionManager).getTransaction(any());
    verify(transactionManager).commit(any());
  }

  @Test
  @DisplayName("2.3 Dans un appel externe")
  void testGreetAlice() {
    when(transactionManager.getTransaction(any())).thenReturn(new SimpleTransactionStatus());

    assertEquals("Hello Alice!", greetingService.greet("Alice"));

    verify(transactionManager).getTransaction(any());
    verify(transactionManager).commit(any());
  }

  @Configuration
  @EnableTransactionManagement
  public static class TestTransactionConfiguration {

    @Bean
    public GreetingService greeter() {
      return new GreetingService(new Greeter(new Hello()));
    }
  }
}
