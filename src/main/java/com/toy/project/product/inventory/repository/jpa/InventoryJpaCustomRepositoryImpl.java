package com.toy.project.product.inventory.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.project.product.inventory.dto.CustomProductInventory;
import com.toy.project.product.inventory.dto.QCustomProductInventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.toy.project.product.inventory.dto.entity.QProductInventory.productInventory;
import static com.toy.project.product.inventory.dto.entity.QProducts.products;



@Slf4j
@Repository
@RequiredArgsConstructor
public class InventoryJpaCustomRepositoryImpl implements InventoryJpaCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public CustomProductInventory retrieveInventoryUnit(String productId) {
        return jpaQueryFactory.select(new QCustomProductInventory(
                        productInventory.productId,
                        products.productName,
                        productInventory.quantity
                ))
                .from(productInventory)
                .innerJoin(products).on(productInventory.productId.eq(products.productId))
                .where(productInventory.productId.eq(productId))
                .fetchOne();
    }

    @Override
    public List<CustomProductInventory> retrieveInventoryList(List<String> productIdList) {
        return jpaQueryFactory.select(new QCustomProductInventory(
                        productInventory.productId,
                        products.productName,
                        productInventory.quantity
                ))
                .from(productInventory)
                .innerJoin(products).on(productInventory.productId.eq(products.productId))
                .where(productInventory.productId.in(productIdList))
                .fetch();
    }
}