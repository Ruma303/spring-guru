package guru.springframework.spring6restmvc.manualdi.controllers;

import guru.springframework.spring6restmvc.manualdi.services.GreetingServiceImpl;
import guru.springframework.spring6restmvc.manualdi.controllers.SetterInjectedController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetterInjectedControllerTest {

    SetterInjectedController setterInjectedController;

    @BeforeEach
    void setUp() {
        setterInjectedController = new SetterInjectedController();
        setterInjectedController.setGreetingService(new GreetingServiceImpl());
    }

    @Test
    void sayGreeting() {
        System.out.println(setterInjectedController.sayGreeting());
    }
}