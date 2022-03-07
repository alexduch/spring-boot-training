package com.github.alexduch.springboottraining.extensions.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<GreetingEntity, String> {}
