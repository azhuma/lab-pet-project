package kz.lab.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class LabPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabPetProjectApplication.class, args);

        // План на занятие модуль 2.5 (занятие 9)
        // отключить security

        // имплементировать command line runner

    }

}
