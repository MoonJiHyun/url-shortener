package ch.study.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {


  @GetMapping("/ping")
  public String test() {
    return "pong";
  }
}
