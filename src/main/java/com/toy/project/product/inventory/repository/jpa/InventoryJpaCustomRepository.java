package com.toy.project.product.inventory.repository.jpa;


import com.toy.project.product.inventory.dto.CustomProductInventory;

import java.util.List;

public interface InventoryJpaCustomRepository {

    CustomProductInventory retrieveInventoryUnit(String productId);
    List<CustomProductInventory> retrieveInventoryList(List<String> productIdList);

}
