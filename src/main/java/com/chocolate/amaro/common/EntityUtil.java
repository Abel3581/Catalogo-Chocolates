package com.chocolate.amaro.common;

import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.response.UserRegisterResponse;

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
}
