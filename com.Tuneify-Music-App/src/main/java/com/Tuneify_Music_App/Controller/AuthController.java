package com.Tuneify_Music_App.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login() {
        return  "this is login api";
    }

    @PostMapping("/register")
    public String register() {
        return "this is register api";
    }
}
