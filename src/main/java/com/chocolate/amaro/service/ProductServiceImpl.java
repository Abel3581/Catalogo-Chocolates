package com.chocolate.amaro.service;

import com.chocolate.amaro.Exception.NotFoundExceptions;
import com.chocolate.amaro.Exception.ParamNotFound;
import com.chocolate.amaro.common.DtoUtil;
import com.chocolate.amaro.common.EntityUtil;
import com.chocolate.amaro.dto.PageDto;
import com.chocolate.amaro.dto.ProductDto;
import com.chocolate.amaro.mapper.ProductMapper;
import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.response.ProductDetailsResponse;
import com.chocolate.amaro.model.response.ProductResponse;
import com.chocolate.amaro.repository.IProductRepository;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import com.chocolate.amaro.service.abstraction.IProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {


    private static final String PRODUCTS_NOT_FOUND_MESSAGE = "Product null.";

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

    @PersistenceContext
    private EntityManager entityManager;
/**
    @Override
    public ProductDetailsResponse getById(Long id) {
        Product product = getProduct(id);
        return EntityUtil.convertTo2(product);
    }
**/
    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Optional<Product> entity = productRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Id de Product no v??lido");
        }
        productMapper.productEntityRefreshValues(entity.get(), productDto);
        Product entitySaved = productRepository.save(entity.get());
        return productMapper.productEntity2Dto(entitySaved);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException{
        Product product = getProduct(id);
            product.setSoftDelete(true);
            productRepository.save(product);

    }

    @Override
    public PageDto<ProductDto> getPage(Integer page, Integer sizePage, String sortBy) throws NotFoundException {
        Pageable pageable = PageRequest.of(page, sizePage, Sort.by(sortBy));
        Page<Product> pageRecovered = productRepository.findAll(pageable);
        Integer totalPages = pageRecovered.getTotalPages();
        Long totalElements = pageRecovered.getTotalElements();

        if (totalPages < page) {
            throw new NotFoundExceptions("The page does not exist.");
        }
        return productMapper.toPageDto(pageRecovered, page,  totalElements, totalPages);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> entities = productRepository.findAll();
        List<ProductDto> result = productMapper.productEntityList2DtoList(entities);
        return result;
    }


    private Product getProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty() || product.get().isSoftDelete()){
            throw new EntityNotFoundException(PRODUCTS_NOT_FOUND_MESSAGE);
        }
        return product.get();
    }

    @Override
    public List<ProductDto> getProductByName(String name) {
        List<Product> product = productRepository.findByName(name);
        List<ProductDto> result = productMapper.productEntityList2DtoList(product);
        return result;
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest, MultipartFile image) {
        Product product = productMapper.convertTo(productRequest,image);
        Product saved = productRepository.save(product);
        ProductResponse result = productMapper.convertToEntity(saved);
        return result;

    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Long idCategory) {
        List<Product> products = productRepository.findAll();
        List<ProductDto> result = productMapper.productEntityList2DtoListId(products, idCategory);
        return result;
    }





}




