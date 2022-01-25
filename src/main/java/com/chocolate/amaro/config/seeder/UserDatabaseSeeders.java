package com.chocolate.amaro.config.seeder;

import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.model.entity.Role;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.repository.IRoleRepository;
import com.chocolate.amaro.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDatabaseSeeders {

    private static final String PASSWORD = "test1234";
    private static final String HOST_EMAIL = "@test.com";
    private static final String DEFAULT_FIRST_NAME = "Test";

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            createRoles();
        }

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            createUsers();
        }
    }

    private void createUsers(ApplicationRole applicationRole) {
        for (int index = 0; index < 10; index++) {
            User user = new User();
            user.setFirstName(DEFAULT_FIRST_NAME);
            user.setLastName(applicationRole.getName() + index);
            user.setEmail(applicationRole.getName() + index + HOST_EMAIL);
            user.setPassword(passwordEncoder.encode(PASSWORD));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName(applicationRole.getFullRoleName()));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    private void createRole(Long id, ApplicationRole applicationRole) {
        Role role = new Role();
        role.setId(id);
        role.setName(applicationRole.getFullRoleName());
        role.setDescription(applicationRole.getName());
        roleRepository.save(role);
    }

    private void createRoles() {
        createRole(1L, ApplicationRole.ADMIN);
        createRole(2L, ApplicationRole.USER);
    }

    private void createUsers() {
        createUsers(ApplicationRole.ADMIN);
        createUsers(ApplicationRole.USER);
    }
}
