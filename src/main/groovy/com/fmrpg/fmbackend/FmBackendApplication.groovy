package com.fmrpg.fmbackend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
class FmBackendApplication {

    static void main(String[] args) {
        SpringApplication.run(FmBackendApplication, args)
    }

}
