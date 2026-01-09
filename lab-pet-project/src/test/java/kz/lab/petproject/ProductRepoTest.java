package kz.lab.petproject;

import kz.lab.petproject.entities.Product;
import kz.lab.petproject.repos.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepoTest {

    @Autowired
    ProductRepo productRepo;

    @Test
    void testAddProduct() {
        Product product = productRepo.save(Product.builder()
                .name("")
                .price(3.14)
                .build());

        // fluent api
        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotNull();

        // standard junit
        Assertions.assertNotNull(product.getId());
    }
}
