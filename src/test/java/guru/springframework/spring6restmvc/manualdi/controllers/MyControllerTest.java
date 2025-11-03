package guru.springframework.spring6restmvc.manualdi.controllers;

import guru.springframework.spring6restmvc.manualdi.controllers.MyController;
import org.junit.jupiter.api.Test;

class MyControllerTest {

    @Test
    void sayGreeting() {
        MyController myController = new MyController();
        System.out.println(myController.sayGreeting());
    }
}