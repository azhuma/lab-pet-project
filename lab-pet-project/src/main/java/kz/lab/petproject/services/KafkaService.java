package kz.lab.petproject.services;

import kz.lab.petproject.models.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @SneakyThrows
    public void sendMessage(ProductCreatedEvent event) {
//        SendResult<String, ProductCreatedEvent> result = kafkaTemplate
//                .send(
//                        "product-created-events-topic",
//                        event.getId(),
//                        event)
//                .get();
//
//        log.info("Partition: {}", result.getRecordMetadata().partition());
//        log.info("Topic: {}", result.getRecordMetadata().topic());
//        log.info("Offset: {}", result.getRecordMetadata().offset());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate
                .send(
                        "product-created-events-topic",
                        event.getId(),
                        event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("***** Error sending message: {}", ex.getMessage());
            } else {
                log.info("***** Message sent successfully: {}", result.getRecordMetadata());
            }
        });

        log.info("***** Exiting from sendMessage method");
    }
}
