package kz.lab.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LabPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabPetProjectApplication.class, args);

        // План на занятие модуль 2.2 (занятие 6)
        // lombok, компиляция, delombok
        // вызов публичного апи: postman, idea endpoints
        // exception: handler, advice, status
        // optional
        // spring data: starter и h2
        // entity и repository
        // небольшой пример юнит-теста
        // mapstruct: маппинг между бесчисленными pojo - можно увидеть при компиляции
        // как вариант также есть ModelMapper - работает динамически через рефлексию
        // dto: bean validation, @Validated, кастомная ошибка
        // entity: bean validation - когда сложно сделать валидацию на dto

    }

}
