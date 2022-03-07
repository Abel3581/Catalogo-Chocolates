package com.chocolate.amaro.service;

import com.chocolate.amaro.model.entity.Role;
import com.chocolate.amaro.repository.IRoleRepository;
import com.chocolate.amaro.service.abstraction.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role finBy(String name) {

        return roleRepository.findByName(name);
    }
}
