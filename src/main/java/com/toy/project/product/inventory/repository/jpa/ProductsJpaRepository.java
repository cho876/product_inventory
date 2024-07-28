package com.toy.project.product.inventory.repository.jpa;

import com.toy.project.product.inventory.dto.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsJpaRepository extends JpaRepository<Products, String>, ProductsJpaCustomRepository {

}
