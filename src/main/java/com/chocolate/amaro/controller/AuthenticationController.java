package com.chocolate.amaro.controller;

import com.chocolate.amaro.Exception.NotFoundExceptions;
import com.chocolate.amaro.Exception.UserAlreadyExistException;
import com.chocolate.amaro.model.entity.User;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IRegisterUserService registerUserService;

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

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

    @GetMapping("/mee")
    public ResponseEntity<User> userLogged() throws NotFoundExceptions {
        return new ResponseEntity<>(userService.getInfoUser(), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserRegisterResponse> infoUserLogged() throws NotFoundExceptions{
        return new ResponseEntity<>(userService.infoUserLogged(), HttpStatus.OK);
    }

}
