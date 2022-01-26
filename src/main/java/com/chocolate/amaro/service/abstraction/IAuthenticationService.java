package com.chocolate.amaro.service.abstraction;

import com.chocolate.amaro.model.request.UserAuthenticationRequest;
import com.chocolate.amaro.model.response.UserAuthenticatedResponse;

public interface IAuthenticationService {
    UserAuthenticatedResponse authentication(UserAuthenticationRequest authRequest);
}
