package guru.springframework.spring6restmvc.autodi.controllers;

import guru.springframework.spring6restmvc.autodi.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyInjectedController {

    @Autowired
    @Qualifier("propertyGreetingService")
    GreetingService greetingService;

    public String sayGreeting() {
        return greetingService.sayGreeting();
    }
}
