package com.chocolate.amaro.service.abstraction;


import com.chocolate.amaro.dto.UserDtoRequest;
import com.chocolate.amaro.dto.UserDtoResponse;
import com.chocolate.amaro.model.entity.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface IUserService {

    User getInfoUser();

    UserDtoResponse update(Long id, UserDtoRequest request);

    void deleted(Long id)throws EntityNotFoundException;

    List<UserDtoResponse> getAllUsers();
}
