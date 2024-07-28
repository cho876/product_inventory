package com.toy.project.product.inventory.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.toy.project.product.input.dto.InventoryRequestDto;
import com.toy.project.product.input.dto.ProductRequestDto;
import com.toy.project.product.inventory.dto.entity.Products;
import com.toy.project.product.output.dto.ProductResponseDto;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class CustomProductInventory {
    private String productId;
    private String productName;
    private Integer quantity;

    @QueryProjection
    public CustomProductInventory(String productId, String productName, Integer quantity){
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }
}
