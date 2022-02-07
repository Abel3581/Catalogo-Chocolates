package com.chocolate.amaro.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Getter @Setter
public class CategoryDto {

    private Long id;

    @NotNull(message = "El nombre no puede estar vacio")
    @Pattern(regexp = "^([a-zA-Z]+)$", message = "Este campo no debe contener números")
    private String name;

    @NotNull
    private String description;

    private String image;

    @CreationTimestamp
    private Timestamp timestamp;


    private boolean softDelete;
}
