package com.chocolate.amaro.dto;

import com.chocolate.amaro.model.entity.Product;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.utils.EnumState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrolleyDto {

    private EnumState enumState;
    private User buyer;
    private List<Product> products = new ArrayList<>();
}
