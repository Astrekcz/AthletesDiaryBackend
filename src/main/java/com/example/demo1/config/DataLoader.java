package com.example.demo1.config;


import com.example.demo1.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.demo1.entity.User;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DistanceRepository distanceRepository;

    @Autowired
    private RunsRepository runsRepository;

    @Autowired
    private WarmUpRepository warmUpRepository;

    @Autowired
    WeekRepository weekRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws Exception{
        loadUsers();
    }


    private void loadUsers() throws Exception {
        InputStream inputStream = new ClassPathResource("startup_data/users.json").getInputStream();
        List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});

        for (User user : users) {
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            }
        }
    }


}
