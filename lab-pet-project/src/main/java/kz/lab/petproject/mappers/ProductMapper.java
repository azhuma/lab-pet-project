package kz.lab.petproject.mappers;


import kz.lab.petproject.entities.Product;
import kz.lab.petproject.models.ProductDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
