package com.fmrpg.fmbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/login-success")
    public ResponseEntity<?> loginSuccess(OAuth2AuthenticationToken authentication) {
        Map<String, Object> userAttributes = authentication.getPrincipal().getAttributes();
        return ResponseEntity.ok(userAttributes);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("You have been logged out");
    }

}
