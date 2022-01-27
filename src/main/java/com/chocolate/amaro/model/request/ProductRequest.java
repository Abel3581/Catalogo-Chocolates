package com.chocolate.amaro.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "name may not be empty")
    private String name;

    @NotBlank(message = "Content may not be empty")
    private String description;

    //@NotBlank
    //@Pattern(regexp = "((http|https)://)?(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)(.jpg|.png|.jpeg)", message="The image URL has invalid format")
    private String image;

    @NotNull(message = "Category may not be empty")
    private long categoryId;

    @NotNull(message = "Price may not be empty")
    private Integer price;
}
