package com.chocolate.amaro.service.abstraction;

import com.chocolate.amaro.Exception.UserAlreadyExistException;
import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.model.response.UserRegisterResponse;

public interface IRegisterUserService {
    UserRegisterResponse register(UserRegisterRequest request) throws UserAlreadyExistException;

}
