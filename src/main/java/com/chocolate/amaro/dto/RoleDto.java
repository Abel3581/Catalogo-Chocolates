package com.chocolate.amaro.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class RoleDto {

    private Long id;
    private String name;
    private String description;
    private Timestamp timestamp;
}
