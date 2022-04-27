package com.chocolate.amaro.controller.category;

import com.chocolate.amaro.common.AbstractBaseIntegrationTest;
import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.request.CategoryRequest;
import com.chocolate.amaro.repository.ICategoryRepository;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

public abstract class AbstractBaseCategoryIntegrationTest extends AbstractBaseIntegrationTest {

    @MockBean
    protected ICategoryRepository categoryRepository;

    protected Category stubCategory(){
        return new Category(
                1L,
                "CategoryTest",
                "CategoryDescriptionTest",
                "UrlImage.jpg",
                new Timestamp(System.currentTimeMillis()),
                false
        );
    }

    protected CategoryRequest exampleCategoryRequest(){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("exampleCategory");
        categoryRequest.setDescription("Description");
        categoryRequest.setImage("ImageUrl.png");
        categoryRequest.setSoftDelete(false);
        categoryRequest.setTimestamp(null);
        return categoryRequest;
    }

    protected CategoryDto categoryDto(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("name");
        categoryDto.setImage("image");
        categoryDto.setDescription("description");
        categoryDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        categoryDto.setSoftDelete(false);
        return categoryDto;
    }

}
