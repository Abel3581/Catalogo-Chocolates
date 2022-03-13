package com.chocolate.amaro.service.abstraction;


import com.chocolate.amaro.dto.UserDtoRequest;
import com.chocolate.amaro.dto.UserDtoResponse;
import com.chocolate.amaro.model.entity.User;

public interface IUserService {

    User getInfoUser();

    UserDtoResponse update(Long id, UserDtoRequest request);
}
