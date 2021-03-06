package com.chocolate.amaro.model.request;


import com.chocolate.amaro.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Locale;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "name may not be empty")
    private String name;

    @NotBlank(message = "description may not be empty")
    private String description;

    private String image;

    @NotNull(message = "Price may not be empty")
    private Double price;

    @NotNull(message = "Category may not be empty")
    private long categoryId;
    private Category category;
    private Timestamp timestamp;

}
