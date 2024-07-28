package com.toy.project.product.output.dto;


import com.toy.project.product.inventory.dto.entity.Products;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryResponseDto {
    private String category;
    private String subCategory;
    private String productId;
    private String productName;


    public static CategoryResponseDto toDto(Products product) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setCategory(product.getCategory());
        dto.setSubCategory(product.getSubCategory());
        return dto;
    }
}
