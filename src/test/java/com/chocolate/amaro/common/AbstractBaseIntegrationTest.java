package com.chocolate.amaro.common;

import ch.qos.logback.classic.joran.ReconfigureOnChangeTaskListener;
import com.chocolate.amaro.model.entity.Role;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.repository.IUserRepository;
import com.chocolate.amaro.service.abstraction.IRoleService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.assertj.core.util.Lists;

import java.sql.Timestamp;

public class AbstractBaseIntegrationTest {

    protected TestRestTemplate restTemplate = new TestRestTemplate();
    protected HttpHeaders headers = new HttpHeaders();

    @MockBean
    protected IUserRepository userRepository;
    @MockBean
    protected AuthenticationManager authenticationManager;
    @MockBean
    protected IRoleService roleService;
    @LocalServerPort
    private int port;

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    protected void login(String role) {
        String jwt = SecurityTestConfig.createToken("johnny@gmail.com", role);
        headers.set("Authorization", jwt);
    }

    protected Role stubRole(String name){
        Role role = new Role();
        role.setName(name);

        return role;
    }

    protected User stubUser(String role){
        return new User(1L,
                "John",
                "Doe",
                "johnny@gmail.com",
                "123456789",
                "Telefono",
                Lists.list(stubRole(role)));
    }

}
