package com.Tuneify_Music_App.Controller;

<<<<<<< HEAD
=======
import com.Tuneify_Music_App.Dto.AuthRequest;
import com.Tuneify_Music_App.Dto.AuthResponse;
>>>>>>> master
import com.Tuneify_Music_App.Dto.RegisterRequest;
import com.Tuneify_Music_App.Dto.UserResponse;
import com.Tuneify_Music_App.Service.UserService;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
>>>>>>> master
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
<<<<<<< HEAD

    @PostMapping("/login")
    public String login() {
        return  "this is login api";
    }

//    register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
          UserResponse response =userService.registerUser(registerRequest);
          return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
         return ResponseEntity.badRequest().body(e.getMessage());
=======
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            return ResponseEntity.ok(new AuthResponse("token", authRequest.getEmail(), "USER"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Email or password incorrect");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            UserResponse response = userService.registerUser(registerRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
>>>>>>> master
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
