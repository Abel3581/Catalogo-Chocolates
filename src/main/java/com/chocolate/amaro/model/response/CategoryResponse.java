package com.chocolate.amaro.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CategoryResponse {

    private long id;
    private String name;
    private String image;
    private String description;
}
