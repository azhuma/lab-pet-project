package kz.lab.kafkaconsumer.handlers;


import kz.lab.kafkaconsumer.models.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(topics = "product-created-events-topic")
public class ProductCreatedEventHandler {

    @KafkaHandler
    public void handle(ProductCreatedEvent event) {
        log.info("Product created event: {}", event);
    }

//    @KafkaHandler
//    public void handle(ProductCreatedEvent event, Acknowledgment acknowledgment) {
//        log.info("Product created event with ack: {}", event);
//
//        if (event != null && event.getPrice() <= 100) {
//            acknowledgment.acknowledge();
//        }
//    }
}
