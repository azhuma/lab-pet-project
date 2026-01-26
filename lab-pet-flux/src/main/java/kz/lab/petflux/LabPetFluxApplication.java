package kz.lab.petflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

@SpringBootApplication(exclude = R2dbcAutoConfiguration.class)
public class LabPetFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabPetFluxApplication.class, args);
    }

}
