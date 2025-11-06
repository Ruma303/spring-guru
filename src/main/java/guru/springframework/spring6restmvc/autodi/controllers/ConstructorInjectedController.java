package guru.springframework.spring6restmvc.autodi.controllers;

import guru.springframework.spring6restmvc.autodi.services.GreetingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ConstructorInjectedController {

    private final GreetingService greetingService;

    public ConstructorInjectedController(@Qualifier("greetingServiceImpl") GreetingService greetingService)
    { this.greetingService = greetingService; }

    public String sayGreeting() {
        return greetingService.sayGreeting();
    }
}
