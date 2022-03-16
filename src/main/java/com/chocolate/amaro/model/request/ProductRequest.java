package com.chocolate.amaro.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "name may not be empty")
    private String name;

    @NotBlank(message = "description may not be empty")
    private String description;

   // @NotBlank(message = "image may not be empty")
    //@Pattern(regexp = "((http|https)://)?(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)(.jpg|.png|.jpeg)", message="The image URL has invalid format")
    private String image;

    @NotNull(message = "Price may not be empty")
    private Long price;

    @NotNull(message = "Category may not be empty")
    private long categoryId;

    private Timestamp timestamp;

}
