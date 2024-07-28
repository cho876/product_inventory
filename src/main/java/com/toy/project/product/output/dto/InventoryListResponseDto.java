package com.toy.project.product.output.dto;


import com.toy.project.product.inventory.dto.CustomProductInventory;
import com.toy.project.product.inventory.dto.entity.Products;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryListResponseDto {
    private Integer page;
    private Integer recordSize;
    private List<InventoryResponseDto> inventoryResponseDtoList;

    public void setResponseFromInventoryList(List<CustomProductInventory> inventoryList) {
        this.setInventoryResponseDtoList(inventoryList.stream()
                .map(InventoryResponseDto::toDto)
                .collect(Collectors.toList()));
    }
}
