package com.github.alexduch.springboottraining.p2_boot;

import com.github.alexduch.springboottraining.extensions.persistence.GreetingEntity;
import com.github.alexduch.springboottraining.extensions.persistence.GreetingRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("5 Tester la couche de persistance")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GreetingPersistenceTest {

  @Autowired private GreetingRepository greetingRepository;

  @Test
  @Order(0)
  void testSave() {
    GreetingEntity newEntity = new GreetingEntity("John", "Hello John!", LocalDateTime.now());

    GreetingEntity saved = greetingRepository.save(newEntity);

    assertThat(saved).extracting(GreetingEntity::getId).isNotNull();
  }

  @Test
  @Order(1)
  void testReadSavedGreeting() {
    assertThat(greetingRepository.findAll()).hasSize(1);
  }
}
