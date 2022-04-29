package com.chocolate.amaro.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CategoryDto {

    private Long id;

    @NotNull(message = "The name attribute must not be empty.")
    @Pattern(regexp = "^([a-zA-Z]+)$", message = "This field must not contain numbers")
    private String name;

    @NotNull
    private String description;

    private String image;

    @CreationTimestamp
    private Timestamp timestamp;

    private boolean softDelete;
}
