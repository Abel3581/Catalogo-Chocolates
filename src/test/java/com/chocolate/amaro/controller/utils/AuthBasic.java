package com.chocolate.amaro.controller.utils;

import com.chocolate.amaro.common.JwtUtil;
import com.chocolate.amaro.service.UserServiceImpl;
import com.chocolate.amaro.service.abstraction.IAuthenticationService;
import com.chocolate.amaro.service.abstraction.IRegisterUserService;
import com.chocolate.amaro.service.abstraction.IRoleService;
import com.chocolate.amaro.service.abstraction.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

public class AuthBasic {

    @MockBean
    protected UserServiceImpl userServiceImp;
    @Autowired
    protected JwtUtil jwtUtil;
    @Autowired
    protected WebApplicationContext context;
    @Autowired
    protected IRoleService roleService;
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected IAuthenticationService authenticationService;
    @Autowired
    protected IRegisterUserService registerUserService;
    @Autowired
    protected IUserService userService;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
}
