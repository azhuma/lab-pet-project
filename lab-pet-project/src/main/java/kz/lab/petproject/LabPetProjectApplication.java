package kz.lab.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
//@EnableAsync
public class LabPetProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabPetProjectApplication.class, args);

        // План на занятие модуль 4.3 (занятие 14)
        // практика с kafka

        // добавить зависимость в пом
        // прописать параметры в properties
        // добавить файл конфигурации бина

        // запустим приложение
        // в терминале зайдем в кафку и запустим
        // docker exec --workdir /opt/kafka/bin/ -it kafka-container sh
        // ./kafka-topics.sh --bootstrap-server localhost:9092 --describe

        // создадим сервис для отправки события при добавлении нового продукта
        // также запусти консьюмер в терминале
        // ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic product-created-events-topic
    }

}
