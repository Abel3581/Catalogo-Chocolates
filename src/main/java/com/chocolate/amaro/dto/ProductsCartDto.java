package com.chocolate.amaro.dto;

import com.chocolate.amaro.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsCartDto {

    private Long idCart;
    private List<Product> products = new ArrayList<>();
}
