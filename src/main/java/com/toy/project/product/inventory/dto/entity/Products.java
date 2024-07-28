package com.toy.project.product.inventory.dto.entity;


import com.toy.project.product.input.dto.ProductRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products extends AbstractEntity {

    @Id
    @Column(length = 50)
    private String productId;

    @Column
    private String productName;

    @Column(length = 500)
    private String description;

    @Column(length = 50)
    private String category;

    @Column(length = 50)
    private String subCategory;

    @Column
    private Integer price;

    public static Products initByDto(ProductRequestDto requestDto){
        return Products.builder()
                .productId(requestDto.getProductId())
                .productName(requestDto.getProductName())
                .description(requestDto.getDescription())
                .category(requestDto.getCategory())
                .subCategory(requestDto.getSubCategory())
                .price(requestDto.getPrice())
                .build();
    }

    public static List<Products> initByDtoList(List<ProductRequestDto> requestDtoList){
        return requestDtoList.stream().map(requestDto -> Products.builder()
                        .productId(requestDto.getProductId())
                        .productName(requestDto.getProductName())
                        .description(requestDto.getDescription())
                        .category(requestDto.getCategory())
                        .subCategory(requestDto.getSubCategory())
                        .price(requestDto.getPrice())
                        .build())
                .toList();
    }
}
