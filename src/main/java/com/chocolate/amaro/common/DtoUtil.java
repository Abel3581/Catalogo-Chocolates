package com.chocolate.amaro.common;

import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.request.ProductRequest;
import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.service.abstraction.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DtoUtil {

    public DtoUtil(){}

    @Autowired
    private ICategoryService categoryService;

    public static User convertTo(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setCellphone(userRegisterRequest.getCellphone());
        return user;
    }

    public Product convertTo(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImage(productRequest.getImage());
        product.setTimestamp(new Timestamp(System.currentTimeMillis()));
        Category category = categoryService.getCategory(productRequest.getCategoryId());
        product.setCategory(category);

        return product;
    }



}
