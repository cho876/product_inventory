package com.toy.project.product.batch;


import com.toy.project.product.batch.dummy.DummyData;
import com.toy.project.product.inventory.dto.entity.ProductInventory;
import com.toy.project.product.inventory.dto.entity.Products;
import com.toy.project.product.inventory.repository.jpa.InventoryJpaRepository;
import com.toy.project.product.inventory.repository.jpa.ProductsJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class CsvFileWriter implements ItemWriter<DummyData> {

    private final ProductsJpaRepository productsJpaRepository;
    private final InventoryJpaRepository inventoryJpaRepository;

    @Override
    public void write(Chunk<? extends DummyData> chunk) throws Exception {
        List<Products> productsList = new ArrayList<>();
        List<ProductInventory> productInventoryList = new ArrayList<>();

        chunk.forEach(csvData -> {
            // 상품 기본정보
            Products csvProducts = Products.builder()
                    .category(csvData.getCategory())
                    .subCategory(csvData.getSubCategory())
                    .productId(csvData.getProductId())
                    .productName(csvData.getProductName())
                    .price(csvData.getPrice())
                    .build();

            // 재고 기본정보
            ProductInventory csvProductInventory = ProductInventory.builder()
                    .productId(csvData.getProductId())
                    .quantity(csvData.getQuantity())
                    .build();


            productsList.add(csvProducts);
            productInventoryList.add(csvProductInventory);
        });

        productsJpaRepository.saveAll(productsList);
        inventoryJpaRepository.saveAll(productInventoryList);
    }
}
