package com.chocolate.amaro.service.abstraction;

import com.chocolate.amaro.dto.ProductDto;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductDetailsResponse;
import com.chocolate.amaro.model.response.ProductResponse;


import javax.persistence.EntityNotFoundException;

public interface IProductService {

    ProductResponse addProduct(ProductRequest productRequest);


    ProductDetailsResponse getById(Long id);


    ProductDto update(Long id, ProductDto productDto);

    void delete(Long id) throws EntityNotFoundException;
}
