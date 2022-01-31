package com.chocolate.amaro.common;

import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.response.*;
import org.springframework.stereotype.Component;

@Component
public class EntityUtil {

    public EntityUtil(){}

    public static UserRegisterResponse concertTo(User user){
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setFirstName(user.getFirstName());
        userRegisterResponse.setLastName(user.getLastName());
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setEmail(user.getEmail());
        userRegisterResponse.setPhoto(userRegisterResponse.getPhoto());
        return userRegisterResponse;

    }

    public static ProductResponse convertTo(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setCategory(product.getCategory());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setName(product.getName());
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
