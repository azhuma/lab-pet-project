package kz.lab.petflux.mappers;


import kz.lab.petflux.entities.Product;
import kz.lab.petflux.models.ProductDto;
import kz.lab.petflux.models.ProductPostDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    Product toEntity(ProductPostDto productPostDto);
}
