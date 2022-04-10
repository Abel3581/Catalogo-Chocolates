package com.chocolate.amaro.model.response;

import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class InvoiceResponse {

    private Long id;
    private int amount = 0;
    private Integer quantity = 0;
    List<Product> productList;
    private User user;
}
