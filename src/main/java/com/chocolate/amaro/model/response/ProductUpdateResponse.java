package com.chocolate.amaro.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductUpdateResponse {

    private long id;
    private String name;
    private String description;
    private String image;
    private Integer price;
    private long categoryId;

}
