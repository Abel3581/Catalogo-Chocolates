package com.chocolate.amaro.utils;

import com.chocolate.amaro.model.request.UserRegisterRequest;

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
}
