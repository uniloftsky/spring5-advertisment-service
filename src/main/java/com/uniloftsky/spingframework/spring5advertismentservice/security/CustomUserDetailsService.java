package com.uniloftsky.spingframework.spring5advertismentservice.security;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Expected user not found!");
        }
        User user = userOptional.get();
        return new CustomUserDetails(user);
    }

}
