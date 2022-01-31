package com.chocolate.amaro.service.abstraction;

import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.response.CategoryResponse;

public interface ICategoryService {
    Category getCategory(long categoryId);


    CategoryDto save(CategoryDto categoryDto);
}
