package io.isalazar.products.products.repos;

import io.isalazar.products.products.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductsRepository extends JpaRepository<Products, Long> {
}
