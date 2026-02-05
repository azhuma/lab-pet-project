package kz.lab.petproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCreatedEvent {
    private String id;
    private String name;
    private Double price;
}
