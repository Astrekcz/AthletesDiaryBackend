package com.example.demo1.service;

import com.example.demo1.repository.UserRepository;
import com.example.demo1.dto.AuthenticationRequestDto;
import com.example.demo1.dto.AuthenticationResponseDto;
import com.example.demo1.dto.UserRegisterDto;
import com.example.demo1.entity.Role;
import com.example.demo1.entity.User;
import com.example.demo1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public User register(UserRegisterDto registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken"); // Or create a custom exception
        }
        User user = User.builder()
                .firstname(registerRequest.getFirstName())
                .lastname(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    public User registerAsAdmin(UserRegisterDto registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken"); // Or create a custom exception
        }
        User user = User.builder()
                .firstname(registerRequest.getFirstName())
                .lastname(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMIN)
                .build();

        return userRepository.save(user);
    }

    public User registerAsCoach(UserRegisterDto registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken"); // Or create a custom exception
        }
        User user = User.builder()
                .firstname(registerRequest.getFirstName())
                .lastname(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.COACH)
                .build();

        return userRepository.save(user);
    }



    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequest) {
        User user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail()); // Load UserDetails
            String jwtToken = jwtUtil.generateToken(userDetails, user.getUserId()); // Generate JWT token with userId
            return new AuthenticationResponseDto(jwtToken);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

}
