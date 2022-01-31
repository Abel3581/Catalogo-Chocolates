package com.chocolate.amaro.controller;

import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.model.request.CategoryRequest;
import com.chocolate.amaro.model.response.CategoryResponse;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @PostMapping("")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto){
        CategoryDto categorySaved = service.save(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
    }
}
