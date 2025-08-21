package grioup.eazybank.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demoapp {

    // This is a placeholder for the Demoapp controller.
    // You can add request mapping methods here to handle HTTP requests.

    // Example:
     @GetMapping("/hello")
     public String sayHello() {
         return "Hello, EazyBank!";
     }
}
