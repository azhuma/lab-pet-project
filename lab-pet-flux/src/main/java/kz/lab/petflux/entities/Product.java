package kz.lab.petflux.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {
    @Id
    //private Integer id;
    private String id;

    private String name;

    private Double price;

    private String address;
}
