package com.toy.project.product.input.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품 요청 DTO")
public class ProductRequestDto {
    @Schema(description = "상품ID", nullable = true, example = "12421")
    private String productId;

    @Schema(description = "상품명", nullable = true, example = "비비고 왕교자")
    private String productName;

    @Schema(description = "대분류", nullable = true, example = "10000")
    private String category;

    @Schema(description = "소분류", nullable = true, example = "1001")
    private String subCategory;

    @Schema(description = "설명", nullable = true, example = "비비고 상품 - 왕교자")
    private String description;

    @Schema(description = "가격", nullable = true, example = "9980")
    private Integer price;
}
