package com.chocolate.amaro.service;

import com.chocolate.amaro.common.DtoUtil;
import com.chocolate.amaro.common.EntityUtil;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductResponse;
import com.chocolate.amaro.repository.IProductRepository;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import com.chocolate.amaro.service.abstraction.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private DtoUtil dtoUtil;

    @Autowired
    private EntityUtil entityUtil;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = dtoUtil.convertTo(productRequest);
        Product saved = productRepository.save(product);
        ProductResponse result = entityUtil.convertTo(saved);
        return result;
    }
}
