package com.chocolate.amaro.model.response;

import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class InvoiceResponse {

    private Long id;
    private String nameUser, username, lastname;
    private int totalPrice = 0;
    private Integer quantity = 0;
    private Timestamp timestamp;
    List<Product> productList;
}
