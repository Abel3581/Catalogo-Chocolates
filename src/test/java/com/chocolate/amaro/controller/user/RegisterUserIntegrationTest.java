package com.chocolate.amaro.controller.user;

import com.chocolate.amaro.common.AbstractBaseIntegrationTest;
import com.chocolate.amaro.common.JwtUtil;
import com.chocolate.amaro.common.SecurityTestConfig;
import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.model.response.ErrorResponse;
import com.chocolate.amaro.model.response.UserRegisterResponse;
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
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterUserIntegrationTest extends AbstractBaseIntegrationTest {

    private static final String PATH = "/auth/register";
    private String token = SecurityTestConfig.createToken(
            "johnny@gmail.com",
            ApplicationRole.USER.getFullRoleName());

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void shouldRegisterUserWithTokenSuccessfully(){

        User user = stubUser(ApplicationRole.USER.getFullRoleName());
        when(userRepository.findByEmail(eq("johnny@gmail.com"))).thenReturn(null);
        when(userRepository.save(isA(User.class))).thenReturn(user);
        when(jwtUtil.generateToken(eq(user))).thenReturn(token);

        UserRegisterRequest userDetailsRequest = getUserDetailsRequest();

        ResponseEntity<UserRegisterResponse> response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.POST,
                new HttpEntity<>(userDetailsRequest, headers),
                UserRegisterResponse.class);

        assertNotNull(response.getBody().getToken());
        assertEquals(response.getBody().getEmail(), user.getEmail());
        assertEquals(response.getBody().getFirstName(), user.getFirstName());
        assertEquals(response.getBody().getCellphone(), user.getCellphone());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturnEmailNotAvailable() {
        User user = stubUser(ApplicationRole.USER.getFullRoleName());
        when(userRepository.findByEmail(eq("johnny@gmail.com"))).thenReturn(user);
        UserRegisterRequest userDetailsRequest = getUserDetailsRequest();

        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.POST,
                new HttpEntity<>(userDetailsRequest, headers),
                ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email address is already used.", response.getBody().getMessage());
    }

    private UserRegisterRequest getUserDetailsRequest() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setEmail("johnny@gmail.com");
        registerRequest.setFirstName("john");
        registerRequest.setLastName("Doe");
        registerRequest.setPassword("123456789");
        registerRequest.setCellphone("teléfono");
        return registerRequest;
    }

}
