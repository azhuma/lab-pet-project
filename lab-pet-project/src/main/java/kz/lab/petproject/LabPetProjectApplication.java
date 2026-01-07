package kz.lab.petproject;

import kz.lab.petproject.controllers.SimpleController;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LabPetProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LabPetProjectApplication.class, args);
        SimpleController controller = context.getBean(SimpleController.class);
        controller.hello();
    }

}
