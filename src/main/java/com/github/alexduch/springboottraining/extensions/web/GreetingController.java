package com.github.alexduch.springboottraining.extensions.web;

import com.github.alexduch.springboottraining.core.Greeter;
import com.github.alexduch.springboottraining.extensions.persistence.GreetingEntity;
import com.github.alexduch.springboottraining.extensions.persistence.GreetingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/greet")
public class GreetingController {

  private final Greeter greeter;
  private final GreetingRepository greetingRepository;

  public GreetingController(Greeter greeter, GreetingRepository greetingRepository) {
    this.greeter = greeter;
    this.greetingRepository = greetingRepository;
  }

  @GetMapping
  public String greet(@RequestParam(defaultValue = "world") String name) {
    String greeting = greeter.greet(name);
    return greetingRepository
        .save(new GreetingEntity(name, greeting, LocalDateTime.now()))
        .getGreeting();
  }
}
