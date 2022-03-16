package com.chocolate.amaro.mapper;

import com.chocolate.amaro.dto.PageDto;
import com.chocolate.amaro.dto.ProductDto;
import com.chocolate.amaro.dto.ProductFiltersDto;
import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductResponse;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private ICategoryService categoryService;

    public void productEntityRefreshValues(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setCategory(categoryService.getCategory(productDto.getCategoryId()));
    }

    public ProductDto productEntity2Dto(Product entity) {

        ProductDto dto = new ProductDto();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setImage(entity.getImage());
        return dto;

    }


    public PageDto<ProductDto> toPageDto(Page<Product> productPage, Integer pageNumber, Long totalElements, Integer totalPages) {
        PageDto<ProductDto> pageDto = new PageDto<>();

        if (productPage.hasNext()) {
            pageDto.setNextPage("/product/page?page=" + (pageNumber + 1));
        }

        if (productPage.hasPrevious()) {
            pageDto.setPreviousPage("/product/page?page=" + (pageNumber - 1));
        }
        pageDto.setList(memberEntityList2memberDtoList((productPage)));
        pageDto.setTotalElements(totalElements);
        pageDto.setTotalPages(totalPages);

        return pageDto;
    }

    private List<ProductDto> memberEntityList2memberDtoList(Page<Product> productsPage) {
        List<ProductDto> membersDto = new ArrayList<>();

        if (productsPage.hasContent()) {
            membersDto = productsPage.stream().map(member -> {
                return new ProductDto(member.getId(), member.getName(), member.getImage(), member.getDescription(), member.getPrice(), member.getCategory().getId(),member.getCategory());
            }).collect(Collectors.toList());
        }
        return membersDto;
    }

    public List<ProductDto> productEntityList2DtoList(List<Product> entities) {
        List<ProductDto> dtos = new ArrayList<>();
        ProductDto productDto;
        for (Product entity : entities) {
            productDto = new ProductDto();
            productDto.setId(entity.getId());
            productDto.setName(entity.getName());
            productDto.setImage(entity.getImage());
            productDto.setPrice(entity.getPrice());
            productDto.setDescription(entity.getDescription());
            productDto.setCategory(entity.getCategory());
            dtos.add(productDto);
        }
        return dtos;
    }

    public List<ProductDto> productEntitySet2DtoList(List<Product> entities) {
        List<ProductDto> dtos = new ArrayList<>();
        for (Product entity: entities){
            dtos.add(productEntityBasic2Dto(entity));
        }
        return dtos;
    }
    public ProductDto productEntityBasic2Dto(Product entity) {

        ProductDto dto = new ProductDto();
        dto.setCategoryId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setImage(entity.getImage());
        return dto;

    }

    public List<ProductFiltersDto> productEntityListFilter2Dto(List<Product> entities) {
        List<ProductFiltersDto> dto = new ArrayList<>();
        for (Product entity: entities){
            dto.add(productEntityFilter2Dto(entity));

        }
        return dto;
    }
    public ProductFiltersDto productEntityFilter2Dto(Product entity) {

        ProductFiltersDto dto = new ProductFiltersDto();
        dto.setCategoryId(entity.getCategory().getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());

        return dto;

    }


    public ProductResponse concertToResult(Product product) {
        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setName(product.getName());
        response.setCategory(product.getCategory());
        response.setTimestamp(product.getTimestamp());
        return response;
    }

    public Product convertTo(ProductRequest productRequest, MultipartFile image) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setTimestamp(new Timestamp(System.currentTimeMillis()));
        Category category = categoryService.getCategory(productRequest.getCategoryId());
        product.setCategory(category);

        if(!image.isEmpty()){
           // Path directorioImagenes = Paths.get("src//main//resources//images");
            String rutaAbsoluta = "C://Images//recursos";
            try {
                byte[] bytesImg = image.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta +"//"+ image.getOriginalFilename());
                Files.write(rutaCompleta,bytesImg);
                product.setImage(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return product;
    }

    public ProductResponse convertToEntity(Product product) {
        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setName(product.getName());
        response.setCategory(product.getCategory());
        response.setTimestamp(product.getTimestamp());
        return response;
    }
}