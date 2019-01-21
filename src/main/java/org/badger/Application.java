package org.badger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//testing checkout
@SpringBootApplication
@ComponentScan("org.badger")
public class Application {

  //branch commit test 4
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
