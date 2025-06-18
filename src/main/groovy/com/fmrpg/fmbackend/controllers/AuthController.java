package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class AuthController {


    private final UserService userService;

    AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login-success")
    public ResponseEntity<String> registerUser(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (userService.findUserByOauthId(oauth2User.getName()) == null) {
            userService.registerUser(oauth2User);
            return ResponseEntity.ok("Usuário registrado");
        }

        return ResponseEntity.ok("Usuário logado.");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("You have been logged out");
    }

}
