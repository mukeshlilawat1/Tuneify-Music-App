package com.Tuneify_Music_App.Controller;

import com.Tuneify_Music_App.Document.User;
import com.Tuneify_Music_App.Dto.AuthRequest;
import com.Tuneify_Music_App.Dto.AuthResponse;
import com.Tuneify_Music_App.Dto.RegisterRequest;
import com.Tuneify_Music_App.Dto.UserResponse;
import com.Tuneify_Music_App.Service.AppUserDetailsService;
import com.Tuneify_Music_App.Service.UserService;
import com.Tuneify_Music_App.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService appUserDetailsService;
    private final JwtUtil jwtUtil;

    //    register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            UserResponse response = userService.registerUser(registerRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
//            authenticate the user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

//            load user details
         UserDetails userDetails = appUserDetailsService.loadUserByUsername(authRequest.getEmail());
            User existingUser = userService.findByEmail(authRequest.getEmail());

//            generate jwt token
           String token = jwtUtil.generateToken(userDetails, existingUser.getRole().name());
            return ResponseEntity.ok(new AuthResponse(token, authRequest.getEmail(), existingUser.getRole().name()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Email or password incorrect");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}