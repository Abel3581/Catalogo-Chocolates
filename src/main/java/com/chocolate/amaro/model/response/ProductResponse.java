package com.chocolate.amaro.model.response;

import com.chocolate.amaro.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String image;
    private Double price;
    private Timestamp timestamp;
    private Category category;



}
