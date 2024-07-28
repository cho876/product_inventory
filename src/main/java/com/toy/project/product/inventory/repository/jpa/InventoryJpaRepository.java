package com.toy.project.product.inventory.repository.jpa;

import com.toy.project.product.inventory.dto.entity.ProductInventory;
import com.toy.project.product.inventory.dto.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepository extends JpaRepository<ProductInventory, String>, InventoryJpaCustomRepository {

}
