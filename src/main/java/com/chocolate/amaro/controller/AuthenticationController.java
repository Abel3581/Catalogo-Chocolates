package com.chocolate.amaro.controller;

import com.chocolate.amaro.Exception.UserAlreadyExistException;
import com.chocolate.amaro.model.request.UserAuthenticationRequest;
import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.model.response.UserAuthenticatedResponse;
import com.chocolate.amaro.model.response.UserRegisterResponse;
import com.chocolate.amaro.service.abstraction.IAuthenticationService;
import com.chocolate.amaro.service.abstraction.IRegisterUserService;
import com.chocolate.amaro.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    IRegisterUserService registerUserService;

    @Autowired
    IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request)throws UserAlreadyExistException {
        UserRegisterResponse userRegisterResponse = registerUserService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticatedResponse> login(@Valid @RequestBody UserAuthenticationRequest authenticationRequest){
        UserAuthenticatedResponse response = authenticationService.authentication(authenticationRequest);
        return ResponseEntity.ok(response);
    }

}
