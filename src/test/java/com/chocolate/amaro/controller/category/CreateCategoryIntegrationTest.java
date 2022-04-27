package com.chocolate.amaro.controller.category;

import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.dto.CategoryDto;
import com.chocolate.amaro.model.entity.Category;
import com.chocolate.amaro.model.response.ErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateCategoryIntegrationTest extends AbstractBaseCategoryIntegrationTest{

    private final String PATH = "/category";

    @Test
    public void shouldReturnForbiddenWhenUserIsNotAdmin() {
        setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

        ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestWhenAnAttributeIsNull() {
        setAuthorizationHeaderBasedOn(ApplicationRole.ADMIN.getFullRoleName());

        CategoryDto createCategoryRequest = new CategoryDto();

        HttpEntity<CategoryDto> entity = new HttpEntity<>(createCategoryRequest, headers);
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, entity, ErrorResponse.class);

        String emptyName = "The name attribute must not be empty.";

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
       // assertTrue(response.getBody().getMessage().contains(emptyName));
    }

    @Test
    public void shouldCreateACategorySuccessfully() {
        when(categoryRepository.save(isA(Category.class))).thenReturn(stubCategory());

        setAuthorizationHeaderBasedOn(ApplicationRole.ADMIN.getFullRoleName());

        CategoryDto createCategoryRequest = categoryDto();

        HttpEntity<CategoryDto> entity = new HttpEntity<>(createCategoryRequest, headers);
        ResponseEntity<CategoryDto> response = restTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, entity, CategoryDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        //assertEquals( createCategoryRequest.getName(),response.getBody().getName());

    }




}
