package kz.lab.petflux.repos;

import kz.lab.petflux.entities.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {
}
