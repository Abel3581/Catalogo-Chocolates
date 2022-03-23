package com.chocolate.amaro.service.abstraction;


import com.chocolate.amaro.dto.TrolleyDto;
import com.chocolate.amaro.model.entity.User;


public interface ITrolleyService {

    TrolleyDto addCart(TrolleyDto cart);

    TrolleyDto addProduct(Long cartId, Long productId);

    TrolleyDto getCartById(Long id, User loggerUser);

    void removeProduct(Long cartID, Long productID);
}
