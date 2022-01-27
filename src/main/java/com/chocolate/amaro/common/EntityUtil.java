package com.chocolate.amaro.common;

import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.response.ProductResponse;
import com.chocolate.amaro.model.response.UserRegisterResponse;
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
        response.setCategory(product.getCategory().getId());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setName(product.getName());
        return response;
    }
}
