package com.Tuneify_Music_App.Controller;

import com.Tuneify_Music_App.Dto.RegisterRequest;
import com.Tuneify_Music_App.Dto.UserResponse;
import com.Tuneify_Music_App.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public String login() {
        return  "this is login api";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
          UserResponse response =userService.registerUser(registerRequest);
          return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
         return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
