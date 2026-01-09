package kz.lab.petproject.services;

import kz.lab.petproject.models.ProductDto;
import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<ProductDto> listProducts();
    Optional<ProductDto> getProduct(int id);
    ProductDto addProduct(ProductDto productDto);
}
