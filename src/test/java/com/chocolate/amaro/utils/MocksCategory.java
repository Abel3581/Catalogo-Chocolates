package com.chocolate.amaro.utils;

import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.model.entity.Category;

public class MocksCategory {
    public static Category buildCategoryEntity(){
        return Category.builder()
                .name("name")
                .description("description")
                .softDelete(false)
                .image("image")
                .build();
    }

    public static CategoryDto buildCategoryDTO(){
        return CategoryDto.builder()
                .name("name")
                .description("description")
                .image("image")
                .softDelete(false)
                .build();
    }
}
