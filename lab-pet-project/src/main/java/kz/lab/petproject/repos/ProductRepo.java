package kz.lab.petproject.repos;

import kz.lab.petproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    public List<Product> findAllByNameContainsIgnoreCase(String name);
}
