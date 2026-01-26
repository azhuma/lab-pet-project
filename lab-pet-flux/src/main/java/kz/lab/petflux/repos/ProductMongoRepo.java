package kz.lab.petflux.repos;

import kz.lab.petflux.entities.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface ProductMongoRepo extends ReactiveMongoRepository<Product, Integer> {
}
