package com.chocolate.amaro.service;

import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.mapper.CategoryMapper;
import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.response.CategoryResponse;
import com.chocolate.amaro.repository.ICategoryRepository;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found.";

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category getCategory(long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty() || categoryOptional.get().isSoftDelete()){
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
        }
        return categoryOptional.get();
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category entity = categoryMapper.categoryDto2Entity(categoryDto);
        Category entitySaved = categoryRepository.save(entity);
        CategoryDto result = categoryMapper.categoryEntity2Dto(entitySaved);
        return result;
    }


}
