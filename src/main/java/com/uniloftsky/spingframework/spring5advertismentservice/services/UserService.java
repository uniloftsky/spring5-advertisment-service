package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface UserService extends GenericService<User, Long> {

    @Override
    Set<User> findAll();

    User findByUsername(String username);

    @Override
    User findById(Long aLong);

    @Override
    User save(User obj);

    User save(User obj, MultipartFile file) throws IOException;

    @Override
    void delete(User obj);
}
