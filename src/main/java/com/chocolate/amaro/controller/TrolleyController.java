package com.chocolate.amaro.controller;

import com.chocolate.amaro.dto.TrolleyDto;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.service.abstraction.ITrolleyService;
import com.chocolate.amaro.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/carts/{cartID}/products/{productID}")
    public ResponseEntity<Void> removeProduct(@PathVariable("cartID") Long cartID,
                                              @PathVariable("productID") Long productID){
        service.removeProduct(cartID, productID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO version de prueba
    @GetMapping("/toBuy/cart/{id}")
    public ResponseEntity<TrolleyDto> toBuyCartById(@PathVariable Long id){
        User user = userService.getInfoUser();
        TrolleyDto result = service.BuyProductsById(id, user);
        return ResponseEntity.ok().body(result);
    }


}
