package com.fmrpg.fmbackend;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the FM Backend!";
    }


}
