package com.toy.project.product.inventory.repository.jpa;


import com.toy.project.product.inventory.dto.entity.Products;

import java.util.List;

public interface ProductsJpaCustomRepository {

    List<Products> retrieveProductListByCategory(String category);

    List<Products> retrieveProductListBySubCategory(String category, String subCategory);
}
