package com.github.alexduch.springboottraining.p2_boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@DisplayName("4 Tester la couche web")
class GreeterApiTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void testDefaultGreeting() throws Exception {
    mockMvc
        .perform(get("/greet"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello world!"));
  }

  @Test
  void testGreetingAlice() throws Exception {
    mockMvc
        .perform(get("/greet?name=Alice"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello Alice!"));
  }
}
