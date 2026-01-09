package kz.lab.petproject.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductDto {
    private Integer id;

    @NotNull // отличается от lombok nonnull
    @NotBlank
    private String name;

    private Double price;

}
