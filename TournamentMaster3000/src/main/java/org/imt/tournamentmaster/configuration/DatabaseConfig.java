package org.imt.tournamentmaster.configuration.security;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import static org.imt.tournamentmaster.configuration.security.SecurityConfiguration.ROLE_ADMIN;
import static org.imt.tournamentmaster.configuration.security.SecurityConfiguration.ROLE_USER;

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
                    .roles(ROLE_USER, ROLE_ADMIN)
                    .build();
            userDetailsManager.createUser(admin);
        }
    }
}
