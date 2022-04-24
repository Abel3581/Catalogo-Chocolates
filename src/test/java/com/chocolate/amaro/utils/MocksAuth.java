package com.chocolate.amaro.utils;

import com.chocolate.amaro.model.request.UserAuthenticationRequest;
import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.model.response.UserAuthenticatedResponse;
import com.chocolate.amaro.model.response.UserRegisterResponse;

public class MocksAuth {

    public static UserRegisterRequest buildRegisterRequest(){
        return UserRegisterRequest.builder()
                .firstName("Abel")
                .lastName("Acevedo")
                .email("mock@email.com")
                .cellphone("12345678")
                .password("password")
                .cellphone("12345678")
                .build();
    }

    public static UserAuthenticationRequest buildAuthRequest(){
        return UserAuthenticationRequest.builder()
                .email("mock@email.com")
                .password("password")
                .build();
    }

    public static UserAuthenticatedResponse buildAuthResponse(){
        return UserAuthenticatedResponse.builder()
                .email("admin@gmail.com")
                .token("")
                .build();
    }
}
