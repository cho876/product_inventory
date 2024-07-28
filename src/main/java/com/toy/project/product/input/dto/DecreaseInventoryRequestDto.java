package com.toy.project.product.input.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "재고차감 요청 DTO")
public class DecreaseInventoryRequestDto {
    @Schema(description = "상품ID", nullable = true, example = "12421")
    private String productId;

    @Schema(description = "수량", nullable = true, example = "10")
    private Integer quantity;
}
