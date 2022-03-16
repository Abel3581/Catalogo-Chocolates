package com.chocolate.amaro.controller;

import com.chocolate.amaro.Exception.FieldInvalidException;
import com.chocolate.amaro.dto.PageDto;
import com.chocolate.amaro.dto.ProductDto;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductDetailsResponse;
import com.chocolate.amaro.model.response.ProductResponse;
import com.chocolate.amaro.service.abstraction.IProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private IProductService productService;

    //save a product and its image. Save the image to drive c:Images/resources
    //This type of saving can be improved by eg: aws or another cloud service
    @PostMapping("/save")
    public ResponseEntity<ProductResponse> addProduct(@Valid @ModelAttribute ProductRequest productRequest, @RequestParam("file")
                                                           MultipartFile image) throws FieldInvalidException{
        ProductResponse response = productService.addProduct(productRequest, image);
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

    @GetMapping(value = "/page")
    public ResponseEntity<PageDto<ProductDto>> getPage(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer sizePage,
            @RequestParam(defaultValue = "id") String sortBy) throws NotFoundException {
        return new ResponseEntity<>(productService.getPage(page, sizePage, sortBy), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = productService.getAllProducts();
        return ResponseEntity.ok().body(productDtos);
    }

    @GetMapping("/name")
    public ResponseEntity<List<ProductDto>> getProductByName(@RequestParam String name){
        List<ProductDto> productDetailsResponses = productService.getProductByName(name);
        return ResponseEntity.ok().body(productDetailsResponses);
    }
/**
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductBYCategoryId(@PathVariable Long idCategory){
        List<ProductDto> productsResponse = productService.getProductByCategoryId(idCategory);
        return ResponseEntity.ok().body(productsResponse);
    }
**/


}
