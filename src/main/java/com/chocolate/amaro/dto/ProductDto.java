package com.chocolate.amaro.dto;

import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private long id;
    private String name;
    private String description;
    private String image;
    private Long price;
    private Long categoryId;


}
