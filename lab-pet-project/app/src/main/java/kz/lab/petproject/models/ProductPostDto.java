package kz.lab.petproject.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductPostDto {
    @NotNull
    @NotBlank
    private String name;

    private Double price;

}
