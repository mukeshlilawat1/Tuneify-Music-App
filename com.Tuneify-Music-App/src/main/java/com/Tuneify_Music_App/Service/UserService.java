package com.Tuneify_Music_App.Service;

import com.Tuneify_Music_App.Document.User;
import com.Tuneify_Music_App.Dto.RegisterRequest;
import com.Tuneify_Music_App.Dto.UserResponse;
import com.Tuneify_Music_App.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterRequest registerRequest) {

//        check if email already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

//        create new user
        User newUser = User.builder().email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(User.Role.USER).build();

        userRepository.save(newUser);
        return UserResponse.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .role(UserResponse.Role.USER).build();
    }
}
