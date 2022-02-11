package com.chocolate.amaro.controller;

import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException{
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> categoryDtos = service.getAll();
        return ResponseEntity.ok().body(categoryDtos);
    }

}
