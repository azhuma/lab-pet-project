package kz.lab.petflux.controllers;

import kz.lab.petflux.models.ProductDto;
import kz.lab.petflux.models.ProductPostDto;
import kz.lab.petflux.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v2/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{id}")
    public Mono<ProductDto> getProductById(@PathVariable("id") int id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public Flux<ProductDto> getProducts() {
        System.out.println("i am here: " + Thread.currentThread().getName());
        return productService.listProducts();
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> addProduct(@RequestBody ProductPostDto productPostDto) {
        return productService.addProduct(productPostDto)
                .map(savedDto -> ResponseEntity.created(
                        UriComponentsBuilder
                                .fromHttpUrl("http://localhost:8080/api/v1/product/" + "/" + savedDto.getId())
                                .build().toUri())
                        .build());
    }
}
