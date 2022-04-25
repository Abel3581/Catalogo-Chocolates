package com.chocolate.amaro.integration;

import com.chocolate.amaro.common.AbstractBaseIntegrationTest;
import com.chocolate.amaro.common.JwtUtil;
import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.model.response.ErrorResponse;
import com.chocolate.amaro.model.response.UserRegisterResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;



import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRegisterIntegrationTest extends AbstractBaseIntegrationTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void shouldReturnBadRequestWhenTheEmailAlreadyExist() {
        when(userRepository.findByEmail(eq("johnny@gmail.com"))).thenReturn(new User());

        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("johnny@gmail.com");
        registerRequest.setPassword("123456789");
        registerRequest.setCellphone("Telefono");

        HttpEntity<UserRegisterRequest> entity = new HttpEntity<>(registerRequest, this.headers);
        ResponseEntity<ErrorResponse> response = this.restTemplate.exchange(
                createURLWithPort("/auth/register"), HttpMethod.POST, entity, ErrorResponse.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Email already exist.",response.getBody().getMessage());
    }

    @Test
    public void shouldRegisterSuccessfully() {
        when(roleService.findBy(eq(ApplicationRole.USER.getFullRoleName())))
                .thenReturn(stubRole("USER"));
        when(userRepository.save(isA(User.class))).thenReturn(stubUser("USER"));


        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("johnny@gmail.com");
        registerRequest.setPassword("12346789");

        HttpEntity<UserRegisterRequest> entity = new HttpEntity<>(registerRequest, this.headers);

        ResponseEntity<UserRegisterResponse> response = this.restTemplate.exchange(
                createURLWithPort("/auth/register"), HttpMethod.POST, entity, UserRegisterResponse.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals(response.getBody().getFirstName(), registerRequest.getFirstName());
        Assert.assertEquals(response.getBody().getLastName(), registerRequest.getLastName());
        Assert.assertEquals(response.getBody().getEmail(), registerRequest.getEmail());
        Assert.assertTrue(jwtUtil.validateToken(response.getBody().getToken(), stubUser("USER")));
    }


}
