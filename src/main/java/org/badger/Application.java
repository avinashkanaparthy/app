package org.badger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    System.out.println("Application.main");
    SpringApplication.run(Application.class, args);
  }
}
