package org.imt.tournamentmaster.service;

import org.imt.tournamentmaster.dto.CreateUserDto;
import org.imt.tournamentmaster.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.imt.tournamentmaster.configuration.security.SecurityConfiguration.ROLE_USER;

@Service
public class UserService {
    private final UserDetailsManager userDetailsManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDetailsManager userDetailsManager, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CreateUserDto userDto) {
        if (userDetailsManager.userExists(userDto.getUsername()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        UserDetails user = userMapper.toUser(userDto, hashedPassword, List.of(ROLE_USER));
        this.userDetailsManager.createUser(user);
    }
}
