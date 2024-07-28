package com.toy.project.product.output.dto;


import com.toy.project.product.inventory.dto.entity.Products;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponseDto {
    private List<ProductResponseDto> productResponseDtoList;

    public void setResponseFromProductsList(List<Products> productsList) {
        this.setProductResponseDtoList(productsList.stream()
                .map(ProductResponseDto::toDto)
                .collect(Collectors.toList()));
    }
}
