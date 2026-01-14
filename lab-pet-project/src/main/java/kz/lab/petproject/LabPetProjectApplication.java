package kz.lab.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LabPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabPetProjectApplication.class, args);

        // План на занятие модуль 2.4 (занятие 8)
        // в гитхаб репо добавлен новый проект auth-server
        // его нужно открыть как отдельный проект и запустить
        // он создан на базе spring authorization server
        // https://docs.spring.io/spring-authorization-server/reference/getting-started.html

        // через postman нужно выпустить токен (параметры можно посмотреть в коде авторизационного сервера)
        // grant type: client credentials
        // client id: client1
        // client secret: secret
        // scope: product.call
        // username: user (в хэдере)
        // password: password (в хэдере)

        // получить jwt
        // посмотреть что внутри можно
        // https://www.jstoolset.com/jwt

        
    }

}
