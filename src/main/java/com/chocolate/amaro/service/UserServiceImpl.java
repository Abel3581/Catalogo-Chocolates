package com.chocolate.amaro.service;

import com.chocolate.amaro.Exception.ParamNotFound;
import com.chocolate.amaro.Exception.UserAlreadyExistException;
import com.chocolate.amaro.common.DtoUtil;
import com.chocolate.amaro.common.EntityUtil;
import com.chocolate.amaro.common.JwtUtil;
import com.chocolate.amaro.dto.UserDtoRequest;
import com.chocolate.amaro.dto.UserDtoResponse;
import com.chocolate.amaro.mapper.UserMapper;
import com.chocolate.amaro.model.entity.Role;
import com.chocolate.amaro.model.entity.User;
import com.chocolate.amaro.model.request.UserAuthenticationRequest;
import com.chocolate.amaro.model.request.UserRegisterRequest;
import com.chocolate.amaro.model.response.UserAuthenticatedResponse;
import com.chocolate.amaro.model.response.UserRegisterResponse;
import com.chocolate.amaro.repository.IUserRepository;
import com.chocolate.amaro.config.security.ApplicationRole;
import com.chocolate.amaro.service.abstraction.IAuthenticationService;
import com.chocolate.amaro.service.abstraction.IRegisterUserService;
import com.chocolate.amaro.service.abstraction.IRoleService;
import com.chocolate.amaro.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserDetailsService, IRegisterUserService, IAuthenticationService, IUserService {

    private static final String USER_NOT_FOUND_MESSAGE = "User not found.";
    private static final String USER_EMAIL_ERROR = "Email address is already used.";
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request)throws UserAlreadyExistException {
        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new UserAlreadyExistException(USER_EMAIL_ERROR);
        }
        User user = DtoUtil.convertTo(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.finBy(ApplicationRole.USER.getFullRoleName()));
        user.setRoles(roles);
        User userCreate = userRepository.save(user);
        UserRegisterResponse userRegisterResponse = EntityUtil.concertTo(userCreate);
        userRegisterResponse.setToken(jwtUtil.generateToken(userCreate));
        return userRegisterResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUser(username);
    }

    private User getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty() || userOptional.get().isSoftDelete()) {
            throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        return userOptional.get();
    }

    private User getUser(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        return user;
    }

    @Override
    public UserAuthenticatedResponse authentication(UserAuthenticationRequest authRequest) {
        User user = getUser(authRequest.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));

        return new UserAuthenticatedResponse(jwtUtil.generateToken(user), user.getEmail());
    }

    @Override
    public User getInfoUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String username = ((User)principal).getUsername();
        } else {
            String username = principal.toString();
        }

        return userRepository.findByEmail(principal.toString());

    }

    @Override
    public UserDtoResponse update(Long id, UserDtoRequest request) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new ParamNotFound("User id not valid");
        userMapper.userRefreshValues(userOptional.get(),request);
        User userSaved = userRepository.save(userOptional.get());
        UserDtoResponse response = userMapper.userEntity2Dto(userSaved, true);
        return response;
    }


}
