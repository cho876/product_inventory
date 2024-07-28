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
public class CategoryListResponseDto {
    private int page;
    private int recordSize;
    private List<CategoryResponseDto> categoryProductList;

    public void makeWithPaging(int page, int recordSize, List<Products> productsList) {
       this.page = page;
       this.recordSize = recordSize;
       this.categoryProductList = productsList.stream()
               .map(CategoryResponseDto::toDto)
               .collect(Collectors.toList());
    }
}
