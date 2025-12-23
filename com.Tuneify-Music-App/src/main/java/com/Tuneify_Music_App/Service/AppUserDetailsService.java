package com.Tuneify_Music_App.Service;

import com.Tuneify_Music_App.Document.User;
import com.Tuneify_Music_App.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for the email:"));
      return new org.springframework.security.core.userdetails.User(existingUser.getEmail(), existingUser.getPassword(), getAuthorities(existingUser));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User existingUser) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER" + existingUser.getRole().name()));
    }
}
