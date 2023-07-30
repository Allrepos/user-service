package com.epam.userservice.controller;

import com.epam.userservice.model.UserDto;
import com.epam.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSomeThing() {
        return "Hello this is working";
    }

    @PatchMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUserDetails(@RequestBody UserDto userDto) {
        return userService.updateUserDetails(userDto);
    }

    @PostMapping("/register")
    public boolean registerUser(@RequestBody UserDto userDto) {
        return userService.registerNewUser(userDto);
    }

    @DeleteMapping(path = "/remove")
    public Boolean removeUser(@RequestBody UserDto userDto) {
        return userService.removeUserByEmailId(userDto.getEmailId());
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto loginUser(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto.getEmailId(), userDto.getPassword());
    }


}
