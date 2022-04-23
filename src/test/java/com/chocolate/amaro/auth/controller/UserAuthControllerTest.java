package com.chocolate.amaro.auth.controller;

import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.utils.MocksAuth;

import com.chocolate.amaro.utils.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserAuthControllerTest {

    private static final String PATH = "/auth";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void signUp() throws Exception {

        var request = MocksAuth.buildRegisterRequest();

        //When
      var result = mockMvc.perform(post(PATH + "/register")
              .contentType(MediaType.APPLICATION_JSON)
              .content(TestUtil.toJson(request)));

      //Then
      result.andExpect(status().isCreated());
      result.andExpect(jsonPath("$.firstName").value(request.getFirstName()));
      result.andExpect(jsonPath("$.lastName").value(request.getLastName()));
      result.andExpect(jsonPath("$.email").value(request.getEmail()));
      result.andExpect(jsonPath("$.cellphone").value(request.getCellphone()));
      result.andExpect(jsonPath("$.token").isNotEmpty());
  }
}
