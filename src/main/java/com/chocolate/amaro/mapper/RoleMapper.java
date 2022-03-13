package com.chocolate.amaro.mapper;

import com.chocolate.amaro.dto.RoleDto;
import com.chocolate.amaro.model.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class RoleMapper {


    public List<RoleDto> roleEntitySet2DtoList(Collection<Role> roles) {
        List<RoleDto> dtos = new ArrayList<>();
        for(Role entity : roles){
            dtos.add(this.roleEntity2DTO(entity));
        }
        return dtos;
    }

    private RoleDto roleEntity2DTO(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setTimestamp(entity.getTimestamp());
        return dto;
    }
}
