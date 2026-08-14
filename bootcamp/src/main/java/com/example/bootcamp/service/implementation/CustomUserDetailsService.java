package com.example.bootcamp.service.implementation;

import com.example.bootcamp.exception.ResponseCode;
import com.example.bootcamp.model.security.CustomUserDetails;
import com.example.bootcamp.model.entity.User;
import com.example.bootcamp.exception.types.UserNotFoundException;
import com.example.bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ResponseCode.USER_IS_NOT_FOUND,email, HttpStatus.NOT_FOUND));
        return CustomUserDetails.from(user);
    }
}