package com.toy.project.product.output.dto;


import com.toy.project.product.inventory.dto.entity.Products;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResponseDto {
    private String productId;
    private String productName;
    private String description;
    private String category;
    private String subCategory;
    private Integer price;


    public static ProductResponseDto toDto(Products product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setSubCategory(product.getSubCategory());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
