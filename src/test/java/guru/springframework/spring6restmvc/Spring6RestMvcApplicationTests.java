package guru.springframework.spring6restmvc;

import guru.springframework.spring6restmvc.manualdi.controllers.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Spring6RestMvcApplicationTests {

    @Autowired
    ApplicationContext ctx;

    @Autowired
    MyController myController;

    @Test
    void testAutowireOfController() {
        System.out.println("Response from MyController: " + myController.sayHello());
    }

    @Test
    void testGetControllerFromCtx() {
        MyController myController = ctx.getBean(MyController.class);
        System.out.println("Response from MyController: " + myController.sayHello());
    }

    @Test
    void contextLoads() {
    }
}
