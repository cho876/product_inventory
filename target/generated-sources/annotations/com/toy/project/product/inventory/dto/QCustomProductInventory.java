package com.toy.project.product.inventory.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.toy.project.product.inventory.dto.QCustomProductInventory is a Querydsl Projection type for CustomProductInventory
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCustomProductInventory extends ConstructorExpression<CustomProductInventory> {

    private static final long serialVersionUID = -505332656L;

    public QCustomProductInventory(com.querydsl.core.types.Expression<String> productId, com.querydsl.core.types.Expression<String> productName, com.querydsl.core.types.Expression<Integer> quantity) {
        super(CustomProductInventory.class, new Class<?>[]{String.class, String.class, int.class}, productId, productName, quantity);
    }

}

