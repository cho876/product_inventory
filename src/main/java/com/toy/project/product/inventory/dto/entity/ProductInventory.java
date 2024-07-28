package com.toy.project.product.inventory.dto.entity;


import com.toy.project.product.input.dto.DecreaseInventoryRequestDto;
import com.toy.project.product.input.dto.InventoryRequestDto;
import com.toy.project.product.inventory.dto.CustomProductInventory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventory extends AbstractEntity {

    @Id
    @Column(length = 50)
    private String productId;

    @Column
    private Integer quantity;

    public static ProductInventory initByDto(InventoryRequestDto requestDto) {
        return ProductInventory.builder()
                .productId(requestDto.getProductId())
                .quantity(requestDto.getQuantity())
                .build();
    }

    public static ProductInventory initDecreaseByDto(CustomProductInventory retrievedDto, DecreaseInventoryRequestDto decreaseInventoryRequestDto) {
        return ProductInventory.builder()
                .productId(decreaseInventoryRequestDto.getProductId())
                .quantity(retrievedDto.getQuantity() - decreaseInventoryRequestDto.getQuantity())
                .build();
    }
}
