package com.chocolate.amaro.common;

import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.request.UserRegisterRequest;

public class DtoUtil {

    public static User convertTo(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        user.setEmail(userRegisterRequest.getEmail());
        return user;
    }
}
