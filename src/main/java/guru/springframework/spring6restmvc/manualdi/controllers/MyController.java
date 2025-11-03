package guru.springframework.spring6restmvc.manualdi.controllers;

import guru.springframework.spring6restmvc.manualdi.services.GreetingService;
import guru.springframework.spring6restmvc.manualdi.services.GreetingServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private final GreetingService greetingService;

    public MyController() {
        this.greetingService = new GreetingServiceImpl();
    }

    public String sayGreeting() {
        return greetingService.sayGreeting();
    }

    public String sayHello() {
        System.out.println("Hello from MyController");
        return "Hello";
    }
}
