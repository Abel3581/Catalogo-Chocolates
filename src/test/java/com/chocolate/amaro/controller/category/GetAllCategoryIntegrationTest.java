package com.chocolate.amaro.controller.category;

import com.chocolate.amaro.config.security.ApplicationRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAllCategoryIntegrationTest extends AbstractBaseCategoryIntegrationTest{

    private final Long ID_TO_GET = stubCategory().getId();
    private final String PATH = "/category/all";

    @Test
    public void shouldReturnForbiddenWhenUserIsNotAdmin() {
        setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

        ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }


}
