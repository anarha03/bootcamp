package com.example.bootcamp.service.implementation;

import com.example.bootcamp.exception.ResponseCode;
import com.example.bootcamp.exception.types.UserNotFoundException;
import com.example.bootcamp.model.entity.User;
import com.example.bootcamp.model.security.CustomUserDetails;
import com.example.bootcamp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ResponseCode.USER_IS_NOT_FOUND, email, HttpStatus.NOT_FOUND));
        return CustomUserDetails.from(user);
    }
}