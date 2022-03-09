package com.chocolate.amaro.service.abstraction;

import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.model.entity.Category;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ICategoryService {
    Category getCategory(long categoryId);

    CategoryDto save(CategoryDto categoryDto);

    void delete(Long id)throws EntityNotFoundException;

    List<CategoryDto> getAll();
}
