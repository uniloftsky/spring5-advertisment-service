package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public User findByUsername(String username) {
        if (userRepository.findByUsername(username) == null) {
            throw new RuntimeException("Expected user not found!");
        } else {
            return userRepository.findByUsername(username);
        }
    }

    @Override
    public User findById(Long aLong) {
        Optional<User> userOptional = userRepository.findById(aLong);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Expected user not found!");
        }
        return userOptional.get();
    }

    @Override
    public User save(User obj) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(obj.getPassword());
        obj.setPassword(encodedPassword);
        return userRepository.save(obj);
    }

    @Override
    public void delete(User obj) {
        userRepository.delete(obj);
    }
}
