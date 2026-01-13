package org.imt.tournamentmaster.mapper;

import org.imt.tournamentmaster.dto.CreateUserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public UserDetails toUser(CreateUserDto userDto, String hashedPassword, List<String> authorities) {
        return User
            .builder()
            .username(userDto.getUsername())
            .password(hashedPassword)
            .roles(authorities.toArray(String[]::new))
            .build();
    }
}
