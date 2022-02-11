package com.chocolate.amaro.mapper;

import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.model.entity.Category;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public Category categoryDto2Entity(CategoryDto categoryDto) {
        Category entity = new Category();
        entity.setName(categoryDto.getName());
        entity.setDescription(categoryDto.getDescription());
        entity.setImage(categoryDto.getImage());
        entity.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    public CategoryDto categoryEntity2Dto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setImage(entity.getImage());
        return dto;
    }


    public List<CategoryDto> categoryEntityList2DtoList(List<Category> entity) {
        List<CategoryDto> dtos = new ArrayList<>();
        CategoryDto categoryDto;
        for(Category category: entity){
            categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setImage(category.getImage());
            dtos.add(categoryDto);

        }
        return dtos;
    }
}
