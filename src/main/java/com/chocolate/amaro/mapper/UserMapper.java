package com.chocolate.amaro.mapper;

import com.chocolate.amaro.dto.RoleDto;
import com.chocolate.amaro.dto.UserDtoRequest;
import com.chocolate.amaro.dto.UserDtoResponse;
import com.chocolate.amaro.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void userRefreshValues(User user, UserDtoRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCellphone(request.getCellphone());
        user.setImage(request.getImage());
        user.setEmail(request.getEmail());

    }

    public UserDtoResponse userEntity2Dto(User userSaved, boolean loadRoles) {
        UserDtoResponse response = new UserDtoResponse();
        response.setId(userSaved.getId());
        response.setFirstName(userSaved.getFirstName());
        response.setLastName(userSaved.getLastName());
        response.setCellphone(userSaved.getCellphone());
        response.setPassword(userSaved.getPassword());
        response.setImage(userSaved.getImage());
        response.setEmail(userSaved.getEmail());
        if(loadRoles){
            List<RoleDto>  roleDtoList = roleMapper.roleEntitySet2DtoList(userSaved.getRoles());
            response.setRoleDtoList(roleDtoList);
        }
        return response;
    }

    public List<UserDtoResponse> userEntityList2DTOList(List<User> users, boolean loadRoles) {
        List<UserDtoResponse> dtoResponses = new ArrayList<>();
        UserDtoResponse userDtoResponse;
            for (User user : users) {
                userDtoResponse = new UserDtoResponse();
                userDtoResponse.setId(user.getId());
                userDtoResponse.setFirstName(user.getFirstName());
                userDtoResponse.setLastName(user.getLastName());
                userDtoResponse.setEmail(user.getEmail());
                userDtoResponse.setPassword(user.getPassword());
                userDtoResponse.setImage(user.getImage());
                userDtoResponse.setCellphone(user.getCellphone());
                if (loadRoles) {
                    List<RoleDto> roleDtoList = roleMapper.roleEntitySet2DtoList(user.getRoles());
                    userDtoResponse.setRoleDtoList(roleDtoList);
                }
                dtoResponses.add(userDtoResponse);
            }
        return dtoResponses;
    }

    }


