package com.chocolate.amaro.Integration;

import com.chocolate.amaro.common.AbstractBaseIntegrationTest;
import com.chocolate.amaro.common.JwtUtil;
import com.chocolate.amaro.common.SecurityTestConfig;
import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.model.response.UserRegisterResponse;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterUserIntegrationTest extends AbstractBaseIntegrationTest {

    private static final String PATH = "/auth/register";
    private String token = SecurityTestConfig.createToken(
            "email@gmail.com", ApplicationRole.USER.getFullRoleName());

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void shouldRegisterUserWithTokenSuccessfully(){

        User user = stubUser(ApplicationRole.USER.getFullRoleName());

        when(userRepository.findByEmail(eq("email@gmail.com"))).thenReturn(null);
        when(userRepository.save(isA(User.class))).thenReturn(user);
        when(jwtUtil.generateToken(eq(user))).thenReturn(token);
        UserRegisterRequest userRegisterRequest = getUserRequest(user);

        ResponseEntity<UserRegisterResponse> response = testRestTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.POST,
                new HttpEntity<>(userRegisterRequest, headers),
                UserRegisterResponse.class);

        assertNotNull(response.getBody().getToken());
        assertEquals(response.getBody().getEmail(),user.getEmail());
        assertEquals(response.getBody().getFirstName(), user.getFirstName());
        assertEquals(response.getBody().getId(), user.getId());
        assertEquals(response.getBody().getCellphone(),user.getCellphone());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    private UserRegisterRequest getUserRequest(User userEntity) {
        UserRegisterRequest user = new UserRegisterRequest();
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setPassword(userEntity.getPassword());
        user.setCellphone(userEntity.getCellphone());
        user.setEmail(userEntity.getEmail());
        return user;
    }
}
