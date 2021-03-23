package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;

import java.util.Set;

public interface UserService extends GenericService<User, Long> {

    @Override
    Set<User> findAll();

    @Override
    User findById(Long aLong);

    @Override
    User save(User obj);

    @Override
    void delete(User obj);
}
