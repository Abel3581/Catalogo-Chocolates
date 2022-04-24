package com.chocolate.amaro.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @Builder
public class UserAuthenticatedResponse {

    private String token;
    private String email;

    public UserAuthenticatedResponse(String token, String email){
        this.token = token;
        this.email = email;

    }
}
