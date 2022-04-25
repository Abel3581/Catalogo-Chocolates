package com.chocolate.amaro.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private String cellphone;
    private String token;


}
