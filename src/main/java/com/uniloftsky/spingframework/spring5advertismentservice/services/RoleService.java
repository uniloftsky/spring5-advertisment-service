package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Role;

import java.util.Set;

public interface RoleService extends GenericService<Role, Long> {

    @Override
    Set<Role> findAll();

    @Override
    Role findById(Long aLong);

    @Override
    Role save(Role obj);

    @Override
    void delete(Role obj);
}
