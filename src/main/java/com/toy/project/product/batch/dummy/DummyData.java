package com.toy.project.product.batch.dummy;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DummyData {

    private String category;
    private String subCategory;
    private String productId;
    private String productName;
    private Integer price;
    private Integer quantity;

    public static List<String> getFieldNames() {
        Field[] declaredFields = DummyData.class.getDeclaredFields();

        List<String> result = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            result.add(declaredField.getName());
        }

        return result;
    }
}
