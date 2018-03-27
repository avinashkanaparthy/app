package org.badger;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Handler {

  @RequestMapping("/hello")
  public String hello() {
    //blah
    //language=HTML
    return "<html><body><h1>Hello!</h1></body></html>";
  }
}
