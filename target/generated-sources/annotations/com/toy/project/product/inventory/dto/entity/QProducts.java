package com.toy.project.product.inventory.dto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProducts is a Querydsl query type for Products
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProducts extends EntityPathBase<Products> {

    private static final long serialVersionUID = -598960643L;

    public static final QProducts products = new QProducts("products");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath category = createString("category");

    public final StringPath description = createString("description");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDts = _super.modDts;

    //inherited
    public final StringPath modpeId = _super.modpeId;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productId = createString("productId");

    public final StringPath productName = createString("productName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDts = _super.regDts;

    //inherited
    public final StringPath regpeId = _super.regpeId;

    public final StringPath subCategory = createString("subCategory");

    public QProducts(String variable) {
        super(Products.class, forVariable(variable));
    }

    public QProducts(Path<? extends Products> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProducts(PathMetadata metadata) {
        super(Products.class, metadata);
    }

}

