package kz.lab.petflux.entities;

import lombok.*;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Integer id;

    private String name;

    private Double price;

    private String address;
}
