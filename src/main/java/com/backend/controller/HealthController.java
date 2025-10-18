package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HealthController {

  @GetMapping("/health")
  public ResponseEntity<Void> healthGet() {
    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/health", method = RequestMethod.HEAD)
  public ResponseEntity<Void> healthHead() {
    return ResponseEntity.ok().build();
  }
}
