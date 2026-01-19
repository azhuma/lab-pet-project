package kz.lab.petflux.services;


import kz.lab.petflux.models.ProductDto;
import kz.lab.petflux.models.ProductPostDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService {
    Flux<ProductDto> listProducts();
    Mono<ProductDto> getProduct(int id);
    Mono<ProductDto> addProduct(ProductPostDto productPostDto);
}
