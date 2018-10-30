package org.badger;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Handler {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello() {
    //language=HTML
    return "<html><body><h1>Hello!!</h1></body></html>";
  }

  public void foo1(String a) {
  }

  public void foo(Long c, String a, Integer b) {

  }


  public void foo2(String a, Long c, Integer b) {


    //language=html
    String html = "<html>\n" +
        "<body>\n" +
        "<p>\n" +
        "<table>\n" +
        "    <tr>\n" +
        "        <td></td>\n" +
        "    </tr>\n" +
        "</table>\n" +
        "</p>\n" +
        "</body>\n" +
        "</html>";

    String kjhasgdf = "html";


    if ("BLAH".equalsIgnoreCase(a)) {
    }

  }

  public void bar(Long c, Integer b) {

  }
}
