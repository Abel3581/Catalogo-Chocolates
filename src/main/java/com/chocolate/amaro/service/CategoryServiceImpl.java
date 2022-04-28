package com.chocolate.amaro.service;

import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.mapper.CategoryMapper;
import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.repository.ICategoryRepository;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found.";
    private static final String CATEGORY_NAME_IS_EMPTY = "The name cannot be empty or null.";
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
        return categoryMapper.categoryEntity2Dto(entitySaved);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Category category = getCategory(id);
        category.setSoftDelete(true);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> entity = categoryRepository.findAll();
        if(entity.isEmpty()){
            throw new RuntimeException("the category is empty");
        }
        List<CategoryDto> result = categoryMapper.categoryEntityList2DtoList(entity);
        return result;

    }
}
