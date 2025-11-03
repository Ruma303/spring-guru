package guru.springframework.spring6restmvc.manualdi.controllers;

import guru.springframework.spring6restmvc.manualdi.services.GreetingService;

public class PropertyInjectedController {

    GreetingService greetingService;

    public String sayGreeting() {
        return greetingService.sayGreeting();
    }
}
