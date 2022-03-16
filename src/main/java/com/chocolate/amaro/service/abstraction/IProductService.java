package com.chocolate.amaro.service.abstraction;

import com.chocolate.amaro.dto.PageDto;
import com.chocolate.amaro.dto.ProductDto;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductDetailsResponse;
import com.chocolate.amaro.model.response.ProductResponse;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IProductService {


    ProductDetailsResponse getById(Long id);


    ProductDto update(Long id, ProductDto productDto);

    void delete(Long id) throws EntityNotFoundException;


    PageDto<ProductDto> getPage(Integer page, Integer sizePage, String sortBy)throws NotFoundException;

    List<ProductDto> getAllProducts();


    List<ProductDto> getProductByName(String name);

    //List<ProductDto> getProductByCategoryId(Long idCategory);

    ProductResponse addProduct(ProductRequest productRequest, MultipartFile image);
}
