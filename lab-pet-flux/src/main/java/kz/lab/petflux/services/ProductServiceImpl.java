package kz.lab.petflux.services;


import kz.lab.petflux.mappers.ProductMapper;
import kz.lab.petflux.models.ProductDto;
import kz.lab.petflux.models.ProductPostDto;
import kz.lab.petflux.repos.ProductMongoRepo;
import kz.lab.petflux.repos.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    //private final ProductRepo productRepo;
    private final ProductMongoRepo productRepo;
    private final ProductMapper productMapper;


    @Override
    public Flux<ProductDto> listProducts() {
        return productRepo
                .findAll().limitRate(1)
                .map(productMapper::toDto);
    }

    @Override
    public Mono<ProductDto> getProduct(int id) {
        return productRepo
                .findById(id)
                .map(productMapper::toDto);
    }

    @Override
    public Mono<ProductDto> addProduct(ProductPostDto productPostDto) {
        return productRepo
                .save(productMapper.toEntity(productPostDto))
                .map(productMapper::toDto);
    }
}
