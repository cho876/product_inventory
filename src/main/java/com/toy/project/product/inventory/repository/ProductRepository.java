package com.toy.project.product.inventory.repository;


import com.toy.project.product.inventory.dto.entity.Products;
import com.toy.project.product.inventory.repository.jpa.ProductsJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final ProductsJpaRepository productsJpaRepository;

    public Products retrieveProductUnit(String productId){
        return productsJpaRepository.findById(productId).orElse(null);
    }

    public List<Products> retrieveProductList(List<String> productIdList){
        return productsJpaRepository.findAllById(productIdList);
    }

    public void saveProductUnit(Products saveProducts){
        productsJpaRepository.save(saveProducts);
    }

    public void deleteProductUnit(String productId){
        productsJpaRepository.deleteById(productId);
    }

    public void addProductInfoList(List<Products> addProducts){
        productsJpaRepository.saveAll(addProducts);
    }

    public List<Products> retrieveProductListByCategory(String category){
        return productsJpaRepository.retrieveProductListByCategory(category);
    }

    public List<Products> retrieveProductListBySubCategory(String category, String subCategory){
        log.info("category: {}, sub: {}", category, subCategory);
        return productsJpaRepository.retrieveProductListBySubCategory(category, subCategory);
    }
}
