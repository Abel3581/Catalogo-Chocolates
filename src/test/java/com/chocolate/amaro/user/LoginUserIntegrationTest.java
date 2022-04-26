package com.chocolate.amaro.user;

import com.chocolate.amaro.common.AbstractBaseIntegrationTest;
import com.chocolate.amaro.common.JwtUtil;
import com.chocolate.amaro.common.SecurityTestConfig;
import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.request.UserAuthenticationRequest;
import com.chocolate.amaro.model.response.ErrorResponse;
import com.chocolate.amaro.model.response.UserAuthenticatedResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginUserIntegrationTest extends AbstractBaseIntegrationTest {

    private static final String PATH = "/auth/login";
    private String token = SecurityTestConfig.createToken(
            "johnny@gmail.com",
            ApplicationRole.USER.getFullRoleName());

    @MockBean
    private JwtUtil jwtUtils;

    @Test
    public void shouldLoginUserSuccessfully() {
        User user = stubUser(ApplicationRole.USER.getFullRoleName());
        when(userRepository.findByEmail(eq("johnny@gmail.com"))).thenReturn(user);
        UserAuthenticationRequest userAuthenticationRequest = getUserAuthenticationRequest();
        when(jwtUtils.generateToken(user)).thenReturn(token);

        ResponseEntity<UserAuthenticatedResponse> response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.POST,
                new HttpEntity<>(userAuthenticationRequest, headers),
                UserAuthenticatedResponse.class);

        assertNotNull(response.getBody().getToken());
        assertEquals(response.getBody().getEmail(), user.getEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestWhenUserDoesNotExist() {
        UserAuthenticationRequest userAuthenticationRequest = getUserAuthenticationRequest();
        when(userRepository.findByEmail(eq("johnny@gmail.com"))).thenReturn(null);

        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.POST,
                new HttpEntity<>(userAuthenticationRequest, headers),
                ErrorResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private UserAuthenticationRequest getUserAuthenticationRequest() {
        UserAuthenticationRequest userAuthenticationRequest = new UserAuthenticationRequest();
        userAuthenticationRequest.setEmail("johnny@gmail.com");
        userAuthenticationRequest.setPassword("123456789");
        return userAuthenticationRequest;
    }
}
