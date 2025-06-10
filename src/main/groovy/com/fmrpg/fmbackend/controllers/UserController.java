package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.entities.User;
import com.fmrpg.fmbackend.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String teste() {
        return "Hello, this is the UserController!";
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/register")
    public String registerUser(@AuthenticationPrincipal OAuth2User oauth2User) {
        userService.registerUser(oauth2User);
        return "User registered successfully!";
    }

    @GetMapping("/me")
    public Principal geCurrentUser(Principal principal) {
        return principal;
    }



}
