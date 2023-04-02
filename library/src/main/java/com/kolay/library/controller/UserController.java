package com.kolay.library.controller;

import com.kolay.library.dto.UserSaveDto;
import com.kolay.library.dto.UserShowDto;
import com.kolay.library.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserShowDto addUser(@RequestBody @Valid UserSaveDto userSaveDto) {
        return userService.addUser(userSaveDto);
    }

    @GetMapping
    public List<UserShowDto> findAllUsers() {
        return userService.findAllUsers();
    }

}
