package com.chocolate.amaro.common;

import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.response.*;
import com.chocolate.amaro.repository.ICategoryRepository;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EntityUtil {

    @Autowired
    private static ICategoryRepository categoryRepository;

    @Autowired
    private static ICategoryService categoryService;


    public EntityUtil(){}

    public static UserRegisterResponse concertTo(User user){
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setFirstName(user.getFirstName());
        userRegisterResponse.setLastName(user.getLastName());
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setEmail(user.getEmail());
        userRegisterResponse.setCellphone(user.getCellphone());
        userRegisterResponse.setPhoto(user.getImage());

        return userRegisterResponse;

    }

    public static ProductResponse convertTo(Product product) {
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

    public static ProductDetailsResponse convertTo2(Product product){
        ProductDetailsResponse detailsResponse = new ProductDetailsResponse();
        CategoryResponse categoryResponse = convertTo(product.getCategory());
        detailsResponse.setName(product.getName());
        detailsResponse.setDescription(product.getDescription());
        detailsResponse.setId(product.getId());
        detailsResponse.setImage(product.getImage());
        detailsResponse.setPrice(product.getPrice());
        detailsResponse.setCategory(categoryResponse);
        return detailsResponse;
    }

    public static CategoryResponse convertTo(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setImage(category.getImage());
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }



}
