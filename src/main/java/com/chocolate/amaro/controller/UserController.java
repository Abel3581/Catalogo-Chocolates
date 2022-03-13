package com.chocolate.amaro.controller;

import com.chocolate.amaro.dto.UserDtoRequest;
import com.chocolate.amaro.dto.UserDtoResponse;
import com.chocolate.amaro.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoResponse> update(@PathVariable Long id, @RequestBody UserDtoRequest request){
        UserDtoResponse userResponse = userService.update(id, request);
        return ResponseEntity.ok().body(userResponse);
    }
}
