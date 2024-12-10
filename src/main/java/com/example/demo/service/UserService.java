package com.example.demo.service;

import com.example.demo.repository.TrainingRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingService trainingService;

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserAsAdmin(Long userId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("Authentication object is null");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        if (currentUser.getUserId().equals(userId)) {
            throw new RuntimeException("Admin cannot delete themselves");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));


        userRepository.delete(user);

    }
    @PreAuthorize("hasRole('USER')")
    public void deleteUserAsTheUser(){
        User currentUser = getAuthenticatedUser();

        userRepository.delete(currentUser);

    }
    @PreAuthorize("hasRole('COACH')")
    public List<User> getPupils(){
        return userRepository.findByRole(Role.USER);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("No authentication or principal found");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
    }

}
