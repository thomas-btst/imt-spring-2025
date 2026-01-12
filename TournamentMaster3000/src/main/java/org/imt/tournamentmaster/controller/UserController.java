package org.imt.tournamentmaster.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.imt.tournamentmaster.dto.CreateUserDto;
import org.imt.tournamentmaster.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody @Valid CreateUserDto userDto) {
        userService.createUser(userDto);
    }
}
