package com.toy.project.product.inventory.repository;


import com.toy.project.product.inventory.dto.CustomProductInventory;
import com.toy.project.product.inventory.dto.entity.ProductInventory;
import com.toy.project.product.inventory.dto.entity.Products;
import com.toy.project.product.inventory.repository.jpa.InventoryJpaRepository;
import com.toy.project.product.inventory.repository.jpa.ProductsJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class InventoryRepository {

    private final InventoryJpaRepository inventoryJpaRepository;


    public void saveInventoryUnit(ProductInventory productInventory){
        inventoryJpaRepository.save(productInventory);
    }

    public void deleteInventoryUnit(String productId){
        inventoryJpaRepository.deleteById(productId);
    }

    public CustomProductInventory retrieveInventoryUnit(String productId){
        return inventoryJpaRepository.retrieveInventoryUnit(productId);
    }

    public List<CustomProductInventory> retrieveInventoryList(List<String> productIdList){
        return inventoryJpaRepository.retrieveInventoryList(productIdList);
    }

}
