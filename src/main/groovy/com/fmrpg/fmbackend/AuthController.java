package com.fmrpg.fmbackend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {

    @GetMapping("/")
    public String teste() {
        return "Olá from /";
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
