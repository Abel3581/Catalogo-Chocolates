package com.chocolate.amaro.integration.user;

import com.chocolate.amaro.common.AbstractBaseIntegrationTest;
import com.chocolate.amaro.model.request.UserAuthenticationRequest;
import com.chocolate.amaro.model.response.ErrorResponse;
import com.chocolate.amaro.model.response.UserAuthenticatedResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAuthenticationIntegrationTest extends AbstractBaseIntegrationTest {

    @Test
    public void shouldReturnBadRequestWhenEmailDoesNotHaveRightFormat(){
        UserAuthenticationRequest authenticationRequest = new UserAuthenticationRequest();
        authenticationRequest.setEmail("abc");
        authenticationRequest.setPassword("1234");

        HttpEntity<UserAuthenticationRequest> entity = new HttpEntity<>(authenticationRequest, headers);
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/login"), HttpMethod.POST, entity, ErrorResponse.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Invalid email or password.", response.getBody().getMessage());
    }

    @Test
    public void shouldLoginSuccessfully() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(eq("johnny@gmail.com"))).thenReturn(stubUser("USER"));

        UserAuthenticationRequest authenticationRequest = new UserAuthenticationRequest();
        authenticationRequest.setEmail("johnny@gmail.com");
        authenticationRequest.setPassword("abc1234&");

        HttpEntity<UserAuthenticationRequest> entity = new HttpEntity<>(authenticationRequest, headers);

        ResponseEntity<UserAuthenticatedResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/login"), HttpMethod.POST, entity, UserAuthenticatedResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody().getEmail());
        Assert.assertEquals("johnny@gmail.com", response.getBody().getEmail());
    }
}
