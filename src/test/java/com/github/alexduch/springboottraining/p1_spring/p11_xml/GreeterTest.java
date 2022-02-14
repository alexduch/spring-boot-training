package com.github.alexduch.springboottraining.p1_spring.p11_xml;

import com.github.alexduch.springboottraining.spring.Greeter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:greeter.xml")
class GreeterTest {

  @Autowired(required = false)
  private Greeter greeter;

  @Test
  void name() {
    assertNotNull(greeter);
    assertEquals("Hello World!", greeter.greet("World"));
  }
}
