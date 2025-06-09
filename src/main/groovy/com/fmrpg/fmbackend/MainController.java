package com.fmrpg.fmbackend;


import com.fmrpg.fmbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Welcome to th1e FM Backend!";
    }

    @GetMapping("/principal")
    public String name(Principal principal) {
        return principal.getName();
    }

}
