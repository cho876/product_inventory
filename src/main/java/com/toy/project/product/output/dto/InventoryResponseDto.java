package com.toy.project.product.output.dto;


import com.toy.project.product.inventory.dto.CustomProductInventory;
import com.toy.project.product.inventory.dto.entity.Products;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InventoryResponseDto {
    private String productId;
    private String productName;
    private Integer quantity;


    public static InventoryResponseDto toDto(CustomProductInventory customProductInventory) {
        InventoryResponseDto responseDto = new InventoryResponseDto();
        responseDto.setProductId(customProductInventory.getProductId());
        responseDto.setProductName(customProductInventory.getProductName());
        responseDto.setQuantity(customProductInventory.getQuantity());

        return responseDto;
    }
}
