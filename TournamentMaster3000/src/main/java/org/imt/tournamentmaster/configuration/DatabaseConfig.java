package org.imt.tournamentmaster.configuration.security;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class DatabaseConfig {
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public DatabaseConfig(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        String adminUsername = "admin";
        if (!userDetailsManager.userExists(adminUsername)) {
            UserDetails admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles("USER", "ADMIN")
                    .build();
            userDetailsManager.createUser(admin);
        }
    }
}
