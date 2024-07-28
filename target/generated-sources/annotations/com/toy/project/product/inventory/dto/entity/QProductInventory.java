package com.toy.project.product.inventory.dto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductInventory is a Querydsl query type for ProductInventory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductInventory extends EntityPathBase<ProductInventory> {

    private static final long serialVersionUID = -1865571130L;

    public static final QProductInventory productInventory = new QProductInventory("productInventory");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDts = _super.modDts;

    //inherited
    public final StringPath modpeId = _super.modpeId;

    public final StringPath productId = createString("productId");

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDts = _super.regDts;

    //inherited
    public final StringPath regpeId = _super.regpeId;

    public QProductInventory(String variable) {
        super(ProductInventory.class, forVariable(variable));
    }

    public QProductInventory(Path<? extends ProductInventory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductInventory(PathMetadata metadata) {
        super(ProductInventory.class, metadata);
    }

}

