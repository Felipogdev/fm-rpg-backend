package feiticeiros.example.fmbackend;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @RequestMapping("/")
    public Principal login1(Principal user) {
        return user;
    }

    @RequestMapping("/login")
    public Principal login(Principal user) {
        return user;
    }

}
