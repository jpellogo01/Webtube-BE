package com.Webtube.site.Security;

import com.Webtube.site.Repository.UserRepository;
import com.Webtube.site.Model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DefaultUserConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

        @PostConstruct
        public void initDefaultUser() {
            // Check if the default user already exists
            if (userRepository.findByUsername("admin") == null) {
                // Create a new user with ADMIN role
                User adminUser = new User();
                adminUser.setFullName("Admin");
                adminUser.setUsername("admin");
                // Encode the password using PasswordEncoder
                adminUser.setPassword(passwordEncoder.encode("adminpassword"));
                // Set the ADMIN role
                adminUser.setRole(Role.ADMIN);
                // Save the user
                userRepository.save(adminUser);
            }
    }
}


