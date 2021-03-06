package com.chocolate.amaro.model.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserAuthenticationRequest {

    @Email(message = "Email should have a valid format")
    @NotBlank(message = "Email cannot be empty.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 250, message = "Password should have at least 8 characters")
    private String password;
}
