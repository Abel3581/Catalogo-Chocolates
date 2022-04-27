package com.chocolate.amaro.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "El nombre no puede estar vac-io")
    @Pattern(regexp = "^([a-zA-Z]+)$", message = "Este campo no debe contener números")
    private String name;

    private String description;

    private String image;

    @CreationTimestamp
    private Timestamp timestamp;

    private boolean softDelete;

}
