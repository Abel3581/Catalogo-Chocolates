package com.chocolate.amaro.controller;

import com.chocolate.amaro.Exception.FieldInvalidException;
import com.chocolate.amaro.dto.ProductDto;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductDetailsResponse;
import com.chocolate.amaro.model.response.ProductResponse;
import com.chocolate.amaro.model.response.ProductUpdateResponse;
import com.chocolate.amaro.service.abstraction.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping("")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) throws FieldInvalidException {
        ProductResponse response = productService.addProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsResponse> getDetailsById(@PathVariable Long id){
        ProductDetailsResponse response = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto productDto){
        ProductDto result = productService.update(id, productDto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
