package com.chocolate.amaro.service.abstraction;

import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductResponse;

public interface IProductService {

    ProductResponse addProduct(ProductRequest productRequest);
}
