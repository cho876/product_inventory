package com.toy.project.product.inventory.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.project.product.inventory.dto.entity.Products;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.toy.project.product.inventory.dto.entity.QProducts.products;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductsJpaCustomRepositoryImpl implements ProductsJpaCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Products> retrieveProductListByCategory(String category) {
        return jpaQueryFactory.selectFrom(products)
                .where(products.category.eq(category))
                .orderBy(products.category.asc(), products.subCategory.asc(), products.productId.asc())
                .fetch();
    }

    @Override
    public List<Products> retrieveProductListBySubCategory(String category, String subCategory) {
        return jpaQueryFactory.selectFrom(products)
                .where(products.category.eq(category)
                        .and(products.subCategory.eq(subCategory)))
                .orderBy(products.category.asc(), products.subCategory.asc(), products.productId.asc())
                .fetch();
    }
}