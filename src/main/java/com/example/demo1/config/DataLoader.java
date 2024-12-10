package com.example.demo1.config;


import com.example.demo1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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


    @Override
    public void run(String... args) throws Exception{

    }

}
