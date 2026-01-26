package kz.lab.petflux.repos;

import kz.lab.petflux.entities.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends ReactiveCrudRepository<Product, Integer> {
}
