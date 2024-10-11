package com.bankingapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.bankingapp.model.User;
import com.bankingapp.repository.UserRepository;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Optional<User> existingUser = userRepository.findByUsername("defaultUser");

        if (existingUser.isEmpty()) {  // Используем isEmpty() для Optional
            User user = new User();
            user.setUsername("defaultUser");
            user.setPassword(passwordEncoder.encode("defaultPassword123"));
            userRepository.save(user);

            System.out.println("Default user created: username: defaultUser, password: defaultPassword123");
        } else {
            System.out.println("User 'defaultUser' already exists");
        }
    }
}
