package com.chocolate.amaro.model.response;

import com.chocolate.amaro.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Long price;
    private Category category;

}
