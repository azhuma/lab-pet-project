package kz.lab.petproject.services;

import io.micrometer.common.util.StringUtils;
import kz.lab.petproject.entities.Product;
import kz.lab.petproject.mappers.ProductMapper;
import kz.lab.petproject.models.ProductDto;
import kz.lab.petproject.models.ProductPostDto;
import kz.lab.petproject.repos.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final KafkaService kafkaService;


    @Override
    //@Async
    public List<ProductDto> listProducts(String name) {
        List<Product> list;

        if (StringUtils.isBlank(name)) {
            list = productRepo.findAll();
        } else {
            //list = productRepo.findAllByNameContainsIgnoreCase(name);
            list = productRepo.retrieveProductsWithExactName(name);
        }

        return list
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ProductDto> getProduct(int id) {
        return Optional.ofNullable(
                productMapper.toDto(
                    productRepo
                            .findById(id)
                            .orElse(null)
                )
        );
    }

    @Override
    public ProductDto addProduct(ProductPostDto productPostDto) {
        var result =  productMapper.toDto(
                productRepo.save(
                        productMapper.toEntity(productPostDto)
                )
        );

        kafkaService.sendMessage(productMapper.toEvent(result));

        return result;
    }
}
