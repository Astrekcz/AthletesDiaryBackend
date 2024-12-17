package com.example.demo1.service;


import com.example.demo1.entity.*;
import com.example.demo1.repository.*;
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
    public Duration ConvertToDuration(DurationInput durationInput) {
        return switch (durationInput.getUnit().toLowerCase()){
            case "seconds" -> Duration.ofSeconds(durationInput.getDurationOfRun());
            case "minutes" -> Duration.ofMinutes(durationInput.getDurationOfRun());
            case "hours" -> Duration.ofHours(durationInput.getDurationOfRun());
            default -> throw new IllegalArgumentException("Invalid unit value");
        };
    }

    @PreAuthorize("hasRole('USER')")
    @Transactional
    public Long ConvertTimeToSeconds(DurationInput durationInput) {// time will be saved to database in seconds
        return switch (durationInput.getUnit().toLowerCase()) {
            case "seconds" -> Duration.ofSeconds(durationInput.getDurationOfRun()).toSeconds();
            case "minutes" -> Duration.ofMinutes(durationInput.getDurationOfRun()).toSeconds();
            case "hours" -> Duration.ofHours(durationInput.getDurationOfRun()).toSeconds();
            default -> throw new IllegalArgumentException("Invalid unit value");
        };
    }


    @PreAuthorize("hasRole('USER')")
    @Transactional
    public Long RetrieveTimeFromDto(DurationInput durationInput){
        return switch (durationInput.getUnit().toLowerCase()) {
            case "seconds" -> Duration.ofSeconds(durationInput.getDurationOfRun()).toSeconds();
            case "minutes" -> Duration.ofMinutes(durationInput.getDurationOfRun()).toMinutes();
            case "hours" -> Duration.ofHours(durationInput.getDurationOfRun()).toHours();
            default -> throw new IllegalArgumentException("Invalid unit value");


        };
    }

    /*
    Takže jako první se vyvolá ConvertTimeToSeconds, vteřiny se uloži do database kde pak v rámci potřeby si vyzvedne one vteřiny metoda
    RetrieveTimeFromDto která je sice konvertuje v Long hodnotě na h, min, sec. Dále je controleri kovertuje do duration ConvertToDuration.
*/
    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void saveTraining(String comment, String nameOfTraining,
                             String distance, String exercises,
                             String trot, LocalDate dateOfTrain, Long trainingID,
                             Long pause, String Unit, Long DurationOfRun,
                             Integer repetition, Integer numberOfRuns){

        DurationInput durationInput2 = new DurationInput();

        Long ConvertedTime = ConvertTimeToSeconds(durationInput2);

        durationInput2.setDurationOfRun(ConvertedTime);

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

    @PreAuthorize("hasRole('USER')")
    public void deleteTrainingAsUser(Long trainingID){
        Training training = trainingRepository.findById(trainingID)
                .orElseThrow(() -> new RuntimeException("training record not found"));

        // Retrieve the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("Authentication object is null");
        }

        // Extract user details from the authentication object
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername(); // this is email address

        // Log username for debugging purposes
        log.debug("Username: {}", username);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the review belongs to the logged-in user
        if (!training.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("You can only delete your own reviews");
        }
        trainingRepository.delete(training);
    }


    @PreAuthorize("hasRole('USER')")
    public void editTrainingAsUser(String comment, String nameOfTraining,
                                   String distance, String exercises,
                                   String trot, LocalDate dateOfTrain, Long trainingID,
                                   Long pause, String Unit, Long DurationOfRun,
                                   Integer repetition, Integer numberOfRuns){

        Training training = trainingRepository.findById(trainingID)
                .orElseThrow(() -> new RuntimeException("training record not found"));

        Distance distance1 = distanceRepository.findById(trainingID)
                .orElseThrow(() -> new RuntimeException("distance record not found"));

        DurationInput durationInput = durationInputRepository.findById(trainingID)
                .orElseThrow(() -> new RuntimeException("duration record not found"));

        Runs runs = runsRepository.findById(trainingID)
                .orElseThrow(() -> new RuntimeException("runs record not found"));

        WarmUp warmUp = warmUpRepository.findById(trainingID)
                .orElseThrow(() -> new RuntimeException("warmUp record not found"));


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("Authentication object is null");
        }
        // Extract user details from the authentication object
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername(); // this is email address

        // Log username for debugging purposes
        log.debug("Username: {}", username);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        // Check if the review belongs to the logged-in user
        if (!training.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("You can only edit your own reviews");
        }

        training.setComment(comment);
        training.setNameOfTraining(nameOfTraining);

        runs.setNumberOfRuns(numberOfRuns);
        runs.setPause(pause);
        runs.setRepetition(repetition);

        distance1.setDistance(distance);

        warmUp.setExercises(exercises);
        warmUp.setTrot(trot);

        durationInput.setDurationOfRun(DurationOfRun);
        durationInput.setUnit(Unit);

        trainingRepository.save(training);
        warmUpRepository.save(warmUp);
        runsRepository.save(runs);
        distanceRepository.save(distance1);
        durationInputRepository.save(durationInput);

    }
}
