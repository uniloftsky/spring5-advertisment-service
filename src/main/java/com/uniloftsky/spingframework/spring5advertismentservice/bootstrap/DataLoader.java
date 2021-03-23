package com.uniloftsky.spingframework.spring5advertismentservice.bootstrap;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;
import com.uniloftsky.spingframework.spring5advertismentservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("user", "123456", "test@mail.com", "Google Inc.", "desc", "website", "location");
        userService.save(user);
    }
}
