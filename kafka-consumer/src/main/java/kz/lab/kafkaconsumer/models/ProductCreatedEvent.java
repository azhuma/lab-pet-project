package kz.lab.kafkaconsumer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {
    private String id;
    private String name;
    private Double price;
}
