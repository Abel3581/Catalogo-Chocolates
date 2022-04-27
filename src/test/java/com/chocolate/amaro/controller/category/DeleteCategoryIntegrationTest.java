package com.chocolate.amaro.controller.category;

import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.model.response.ErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteCategoryIntegrationTest extends AbstractBaseCategoryIntegrationTest{

    private final Long ID_TO_DELETE = stubCategory().getId();
    private final String PATH = "/category/" + ID_TO_DELETE;

    @Test
    public void shouldReturnForbiddenWhenUserIsNotAdmin() {
        setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

        ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void shouldReturnNotFoundWhenIdDoesNotExist() {
        when(categoryRepository.findById(eq(ID_TO_DELETE))).thenReturn(Optional.empty());

        setAuthorizationHeaderBasedOn(ApplicationRole.ADMIN.getFullRoleName());

        HttpEntity<CategoryDto> entity = new HttpEntity<>(headers);
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.DELETE, entity, ErrorResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Category not found.", response.getBody().getMessage());
    }

    @Test
    public void shouldSoftDeleteACategorySuccessfully() {
        when(categoryRepository.findById(eq(ID_TO_DELETE))).thenReturn(Optional.of(stubCategory()));
        when(categoryRepository.save(eq(stubCategory()))).thenReturn(stubCategory());

        setAuthorizationHeaderBasedOn(ApplicationRole.ADMIN.getFullRoleName());

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response =
                restTemplate.exchange(createURLWithPort(PATH), HttpMethod.DELETE, entity, Object.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
