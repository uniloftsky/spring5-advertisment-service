package com.uniloftsky.spingframework.spring5advertismentservice.services;

import com.uniloftsky.spingframework.spring5advertismentservice.exceptions.NotFoundException;
import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;

    public UserServiceImpl(UserRepository userRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Користувача з заданим логіном не знайдено");
        } else {
            return userOptional.get();
        }
    }

    @Override
    public Set<User> findAllSortedBy(Comparator<User> comparator) {
        Set<User> users = new TreeSet<>(comparator);
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public User findById(Long aLong) {
        Optional<User> userOptional = userRepository.findById(aLong);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Користувача з заданим ID не знайдено");
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
    public User save(User obj, MultipartFile file) throws IOException {
        if (obj.getId() != null) {
            User user = findById(obj.getId());
            user.setDescription(obj.getDescription());
            user.setEmail(obj.getEmail());
            user.setName(obj.getName());
            user.setOfficeLocation(obj.getOfficeLocation());
            user.setWebsite(obj.getWebsite());
            if (!file.isEmpty()) {
                imageService.setProfileImage(obj, file);
                user.setImg(obj.getImg());
            }
            return userRepository.save(user);
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(obj.getPassword());
            obj.setPassword(encodedPassword);
            imageService.setProfileImage(obj, file);
            return userRepository.save(obj);
        }
    }

    @Override
    public void delete(User obj) {
        userRepository.delete(obj);
    }
}
