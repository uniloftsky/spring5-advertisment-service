package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;

public interface UserService extends GenericService<User, Long> {

    @Override
    Set<User> findAll();

    User findByUsername(String username);

    Set<User> findAllSortedBy(Comparator<User> comparator);

    @Override
    User findById(Long aLong);

    @Override
    User save(User obj);

    User save(User obj, MultipartFile file) throws IOException;

    User changePassword(User user, String password);

    @Override
    void delete(User obj);
}
