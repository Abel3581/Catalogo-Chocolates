package com.chocolate.amaro.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductDetailsResponse {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private CategoryResponse category;


}
