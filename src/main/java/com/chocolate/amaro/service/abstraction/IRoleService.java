package com.chocolate.amaro.service.abstraction;

import com.chocolate.amaro.model.entity.Role;

public interface IRoleService  {

    Role findBy(String name);
}
