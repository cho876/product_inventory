package com.toy.project.product.inventory.dto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAbstractEntity is a Querydsl query type for AbstractEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAbstractEntity extends EntityPathBase<AbstractEntity> {

    private static final long serialVersionUID = -909915010L;

    public static final QAbstractEntity abstractEntity = new QAbstractEntity("abstractEntity");

    public final DateTimePath<java.time.LocalDateTime> modDts = createDateTime("modDts", java.time.LocalDateTime.class);

    public final StringPath modpeId = createString("modpeId");

    public final DateTimePath<java.time.LocalDateTime> regDts = createDateTime("regDts", java.time.LocalDateTime.class);

    public final StringPath regpeId = createString("regpeId");

    public QAbstractEntity(String variable) {
        super(AbstractEntity.class, forVariable(variable));
    }

    public QAbstractEntity(Path<? extends AbstractEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAbstractEntity(PathMetadata metadata) {
        super(AbstractEntity.class, metadata);
    }

}

