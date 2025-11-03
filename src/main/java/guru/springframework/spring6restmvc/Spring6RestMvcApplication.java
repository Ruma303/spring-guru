package guru.springframework.spring6restmvc;

import guru.springframework.spring6restmvc.manualdi.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Spring6RestMvcApplication {

    public static void main(String[] args) {
       ApplicationContext ctx = SpringApplication.run(Spring6RestMvcApplication.class, args);

        MyController controller = ctx.getBean(MyController.class);
        System.out.println("You are in main method");
        System.out.println(controller.sayHello());
    }
}
