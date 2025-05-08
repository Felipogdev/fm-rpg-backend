package feiticeiros.example.fmbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {


    @GetMapping("/login")
    public Principal login(Principal user) {
        return user;
    }


}
