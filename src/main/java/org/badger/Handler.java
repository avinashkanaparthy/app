package org.badger;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Handler {


  String labuda = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions oasdfasdfasdfasdfasdf asdfffdfdfdfdfdfsdfsdfsdfasdfasdfasdfasdfsfdsfsdf wf asdfsdfsv adf dsf sdsdf f Lorem Ipsum.";



  @RequestMapping(value = "/hello", method =
      RequestMethod.GET)
  public String hello() {
    //language=HTML
    return "<html><body><h1>Hello!!</h1></body></html>";
  }

  public void foo() {
  }

  public void foo(String a) {
  }

  public void foo(Long c, String a, Integer b) {
  }

  public void foo(String a, Long c, Integer b) {


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
