package com.example.demo.service;


import com.example.demo.repository.*;
import com.example.demo.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final WarmUpRepository warmUpRepository;
    private final DistanceRepository distanceRepository;
    private final RunsRepository runsRepository;
    private final DurationInputRepository durationInputRepository;




    @PreAuthorize("hasRole('USER')")
    @Transactional
    public Duration saveTime(DurationInput durationInput) {
        return switch (durationInput.getUnit().toLowerCase()){
            case "seconds" -> Duration.ofSeconds(durationInput.getDurationOfRun());
            case "minutes" -> Duration.ofMinutes(durationInput.getDurationOfRun());
            case "hours" -> Duration.ofHours(durationInput.getDurationOfRun());
            default -> throw new IllegalArgumentException("Invalid unit value");
        };
    }

    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void ConvertTimeToSeconds(DurationInput durationInput) {
        if (durationInput.getUnit().equals("minutes")) {
            durationInput.setDurationOfRun(durationInput.getDurationOfRun()*60);
        }else if (durationInput.getUnit().equals("hours")) {
            durationInput.setDurationOfRun(durationInput.getDurationOfRun()*60*60);
        }else if (durationInput.getUnit().equals("seconds")) {
            durationInput.setDurationOfRun(durationInput.getDurationOfRun());
        }else throw new IllegalArgumentException("Invalid unit value");
    }
/*
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public Duration saveTimeToSeconds(Runs input){
        return switch (input.getUnit().toLowerCase()) {
            case "seconds" -> Duration.ofSeconds(input.getDurationOfRun());
            case "minutes" -> Duration.ofMinutes(input.getDurationOfRun());
            case "hours" -> Duration.ofHours(input.getDurationOfRun());
            default -> throw new IllegalArgumentException("Invalid unit type" + input.getUnit());
        };
    }


    @PreAuthorize("hasRole('USER')")
    @Transactional
    public Duration convertToDuration(Runs input){
        return switch (input.getUnit().toLowerCase()) {
            case "seconds" -> Duration.ofSeconds(input.getDurationOfRun());
            case "minutes" -> Duration.ofMinutes(input.getDurationOfRun());
            case "hours" -> Duration.ofHours(input.getDurationOfRun());
            default -> throw new IllegalArgumentException("Invalid unit type" + input.getUnit());
        };
    } */

    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void saveTraining(String comment, String nameOfTraining,
                              String distance, String exercises,
                              String trot, LocalDate dateOfTrain, Long trainingID,
                               Long pause, String Unit, Long DurationOfRun,
                              Integer repetition, Integer numberOfRuns){
        if (DurationOfRun < 0 || DurationOfRun == 0){
            throw new IllegalArgumentException("durationOfRun is invalid");
        }else if (pause < 0 ){
            throw new IllegalArgumentException("pause is invalid");
        }


        Training training1 = trainingRepository.findById(trainingID)
                .orElseThrow(() -> new RuntimeException("training record not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //this is email address

        //load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(username));

        WarmUp warmUp = WarmUp.builder()
                .trot(trot)
                .exercises(exercises)
                .build();

        Training training = Training.builder()
                .dateOfTrain(dateOfTrain)
                .nameOfTraining(nameOfTraining)
                .comment(comment)
                .build();

        Distance distances = Distance.builder()
                .distance(distance)
                .build();

        Runs runs = Runs.builder()
                .pause(pause)
                .repetition(repetition)
                .numberOfRuns(numberOfRuns)
                .build();

        DurationInput durationInput = DurationInput.builder()
                .DurationOfRun(DurationOfRun)
                .Unit(Unit)
                .build();




        //Setting relation between databases
        warmUp.setTraining(training);
        distances.setRuns(runs);
        runs.setTraining(training);
        training.setUser(user);

        // saving to repositories
        trainingRepository.save(training);
        warmUpRepository.save(warmUp);
        runsRepository.save(runs);
        distanceRepository.save(distances);
        durationInputRepository.save(durationInput);


    }
    public List<Training> getTrainingList(){
        return trainingRepository.findAll();
    }


}
