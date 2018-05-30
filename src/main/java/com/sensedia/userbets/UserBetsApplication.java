package com.sensedia.userbets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class UserBetsApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserBetsApplication.class, args);
  }
}
