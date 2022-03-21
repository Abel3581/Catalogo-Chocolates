package com.chocolate.amaro.dto;

import com.chocolate.amaro.model.entity.Category;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Long categoryId;
    private Category category;


}
