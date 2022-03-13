package com.chocolate.amaro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String photo;

    private String cellphone;

    private List<RoleDto> roleDtoList;
}
