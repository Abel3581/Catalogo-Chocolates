package com.chocolate.amaro.controller;

import com.chocolate.amaro.dto.TrolleyDto;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.service.abstraction.ITrolleyService;
import com.chocolate.amaro.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trolley")
public class TrolleyController {

    @Autowired
    private ITrolleyService service;
    @Autowired
    private IUserService userService;

    @PostMapping("")
    public ResponseEntity<TrolleyDto> addCart(@ModelAttribute TrolleyDto cart ){
        TrolleyDto trolleyDto = service.addCart(cart);
        return ResponseEntity.ok().body(trolleyDto);
    }

    @PutMapping("/carts/{cartId}/products/{productId}")
    public ResponseEntity<TrolleyDto> addProduct(@PathVariable Long cartId, @PathVariable Long productId){
        TrolleyDto trolleyDto = service.addProduct(cartId, productId);
        return ResponseEntity.ok().body(trolleyDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrolleyDto> getCartById(@PathVariable Long id){
        User loggerUser = userService.getInfoUser();
        TrolleyDto result = service.getCartById(id, loggerUser);
        return ResponseEntity.ok().body(result);
    }

}
