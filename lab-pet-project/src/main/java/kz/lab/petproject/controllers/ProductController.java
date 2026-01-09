package kz.lab.petproject.controllers;

import kz.lab.petproject.exceptions.NotFoundException;
import kz.lab.petproject.models.ProductDto;
import kz.lab.petproject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable("id") int id) {
        //throw new NotFoundException();
        return productService.getProduct(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.listProducts();
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductDto productDto) {

        ProductDto product = productService.addProduct(productDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",  "/api/v1/product/" + product.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(){
        return ResponseEntity.notFound().build();
    }
}
