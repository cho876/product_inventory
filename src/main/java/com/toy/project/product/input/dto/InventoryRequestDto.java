package com.toy.project.product.input.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "상품재고 요청 DTO")
public class InventoryRequestDto {
    @Schema(description = "상품ID", nullable = true, example = "12421")
    private String productId;

    @Schema(description = "수량", nullable = true, example = "10")
    private Integer quantity;
}
