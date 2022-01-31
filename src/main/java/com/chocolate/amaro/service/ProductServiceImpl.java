package com.chocolate.amaro.service;

import com.chocolate.amaro.Exception.ParamNotFound;
import com.chocolate.amaro.common.DtoUtil;
import com.chocolate.amaro.common.EntityUtil;
import com.chocolate.amaro.dto.ProductDto;
import com.chocolate.amaro.mapper.ProductMapper;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductDetailsResponse;
import com.chocolate.amaro.model.response.ProductResponse;
import com.chocolate.amaro.model.response.ProductUpdateResponse;
import com.chocolate.amaro.repository.IProductRepository;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import com.chocolate.amaro.service.abstraction.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private static final String NEWS_NOT_FOUND_MESSAGE = "News not found.";

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private DtoUtil dtoUtil;

    @Autowired
    private EntityUtil entityUtil;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = dtoUtil.convertTo(productRequest);
        Product saved = productRepository.save(product);
        ProductResponse result = EntityUtil.convertTo(saved);
        return result;
    }

    @Override
    public ProductDetailsResponse getById(Long id) {
        Product product = getProduct(id);
        return EntityUtil.convertTo2(product);
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Optional<Product> entity = productRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Id de Product no válido");
        }
        productMapper.productEntityRefreshValues(entity.get(), productDto);
        Product entitySaved = productRepository.save(entity.get());
        ProductDto result = productMapper.productEntity2Dto(entitySaved);
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    private Product getProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty() || product.get().isSoftDelete()){
            throw new EntityNotFoundException(NEWS_NOT_FOUND_MESSAGE);
        }
        return product.get();
    }
}
