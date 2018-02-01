package org.badger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.badger")
public class Application {

  //branch commit test 2
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
