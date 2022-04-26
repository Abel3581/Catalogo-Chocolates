package com.chocolate.amaro.user;

import com.chocolate.amaro.common.AbstractBaseIntegrationTest;
import com.chocolate.amaro.common.SecurityTestConfig;
import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.response.ErrorResponse;
import com.chocolate.amaro.model.response.UserRegisterResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetUserByAuthorizationHeaderIntegrationTest extends AbstractBaseIntegrationTest {


    private static final String PATH = "/auth/me";
    private String token = SecurityTestConfig.createToken(
            "johnny@gmail.com",
            ApplicationRole.USER.getFullRoleName());

    @Test
    public void shouldReturnUserAboutMeSuccessfully() {
        User user = stubUser(ApplicationRole.USER.getFullRoleName());
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

        ResponseEntity<UserRegisterResponse> response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.GET,
                new HttpEntity<>(token, headers),
                UserRegisterResponse.class);

        assertNotNull(response.getBody());
        assertEquals(response.getBody().getEmail(), user.getEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestWhenUserDoesNotExist() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.GET,
                new HttpEntity<>(token, headers),
                ErrorResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
