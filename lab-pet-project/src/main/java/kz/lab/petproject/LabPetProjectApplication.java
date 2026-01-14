package kz.lab.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LabPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabPetProjectApplication.class, args);

        // План на занятие модуль 2.3 (занятие 7)
        // c прошлого занятия:
        // - h2 консоль
        // - дто для post: отдельно, какие еще варианты?
        // - query param -> jpa custom query, jpql

        // postgresql в контейнере: docker desktop
        // docker run --name postgres-container -e POSTGRES_PASSWORD=simple -p 5432:5432 -d postgres:16
        // настраиваем бд в другом профиле
        // ddl-auto для разных бд и сред
        // flyway: миграция (другое популярное решение - liquibase)
        // поддержка транзакций - в jpa реализовано автоматически

        // Open API - нужно следить за совместимостью версий со спринг бутом
        // https://springdoc.org/faq.html#_what_is_the_compatibility_matrix_of_springdoc_openapi_with_spring_boot
        // boot: automatic spec - json or yaml
        // http://localhost:8080/v3/api-docs
        // http://localhost:8080/swagger-ui/index.html#
        // Open API generator
        // https://openapi-generator.tech/

        // RestTemplate - spring bean configuration
    }

}
