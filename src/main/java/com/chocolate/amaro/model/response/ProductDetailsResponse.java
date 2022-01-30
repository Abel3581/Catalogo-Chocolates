package com.chocolate.amaro.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductDetailsResponse {

    private long id;
    private String name;
    private String description;
    private String image;
    private Integer price;
    private CategoryResponse category;


}
