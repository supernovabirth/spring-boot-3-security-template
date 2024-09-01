package xyz.cowtown.mainapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String testEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Authenticated user: " + authentication.getName() + ", Roles: " + authentication.getAuthorities();
    }
}