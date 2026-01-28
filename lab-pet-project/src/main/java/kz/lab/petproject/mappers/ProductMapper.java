package kz.lab.petproject.mappers;


import kz.lab.petproject.entities.Product;
import kz.lab.petproject.models.ProductCreatedEvent;
import kz.lab.petproject.models.ProductDto;
import kz.lab.petproject.models.ProductPostDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    Product toEntity(ProductPostDto productPostDto);

    ProductCreatedEvent toEvent(ProductDto productDto);
}
