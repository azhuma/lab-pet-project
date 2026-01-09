package kz.lab.petproject.services;

import kz.lab.petproject.mappers.ProductMapper;
import kz.lab.petproject.models.ProductDto;
import kz.lab.petproject.repos.ProductRepo;
import lombok.RequiredArgsConstructor;
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


    @Override
    public List<ProductDto> listProducts() {
        return productRepo
                .findAll()
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
    public ProductDto addProduct(ProductDto productDto) {
        return productMapper.toDto(
                productRepo.save(
                        productMapper.toEntity(productDto)
                )
        );
    }
}
