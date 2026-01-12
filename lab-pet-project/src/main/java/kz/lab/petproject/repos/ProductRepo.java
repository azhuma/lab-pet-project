package kz.lab.petproject.repos;

import kz.lab.petproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    // custom query
    public List<Product> findAllByNameContainsIgnoreCase(String name);

    // jpql
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    public List<Product> retrieveProductsWithExactName(String name);
}
