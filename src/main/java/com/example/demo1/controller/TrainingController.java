package com.example.demo1.controller;

import com.example.demo1.dto.*;
import com.example.demo1.entity.Days;
import com.example.demo1.service.TrainingService;
import com.example.demo1.entity.Training;
import com.example.demo1.mapper.TrainingMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/training")
public class TrainingController {
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    @CrossOrigin(origins = "http://localhost:300")
    @GetMapping("/getTrainingsDto")
    public ResponseEntity<List<TrainingDto>> getTrainingsDto() {
        List<TrainingDto> trainingDtoList = new ArrayList<>();
        List<Training> trainingList = trainingService.getTrainingList();
        for (Training training : trainingList) {
            trainingDtoList.add(trainingMapper.TrainingEntityToDto(training));
        }
        return new ResponseEntity<>(trainingDtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/saveTraining")
    public ResponseEntity<String> saveTraining(@Valid @RequestBody TrainingDto trainingDto, @RequestParam Long trainingID, @Valid @RequestBody DistanceDto distanceDto,
                                               @Valid @RequestBody WarmUpDto warmUpDto, @Valid @RequestBody RunsDto runsDto, @Valid @RequestBody DurationInputDto durationInputDto) {
        try {
            trainingService.saveTraining(trainingDto.getComment(), trainingDto.getNameOfTraining(), distanceDto.getDistance(), warmUpDto.getExercises(), warmUpDto.getTrot(),
                    trainingDto.getDateOfTrain(), trainingID, runsDto.getPause(), durationInputDto.getUnit(), durationInputDto.getDurationOfRun(), runsDto.getRepetition(),
                    runsDto.getNumberOfRuns());
            return new ResponseEntity<>("training was saved", HttpStatus.OK);


        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('USER')")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/deleteTraining")
    public ResponseEntity<String> deleteTraining(@RequestParam Long trainingID) {
        try {
            trainingService.deleteTrainingAsUser(trainingID);
            return new ResponseEntity<>("training was deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('USER')")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/editTraining")
    public ResponseEntity<String> editTrainingAsUser(@Valid @RequestBody TrainingDto trainingDto, @RequestParam Long trainingID, @Valid @RequestBody DistanceDto distanceDto,
                                               @Valid @RequestBody WarmUpDto warmUpDto, @Valid @RequestBody RunsDto runsDto, @Valid @RequestBody DurationInputDto durationInputDto) {

        try {
            trainingService.editTrainingAsUser(trainingDto.getComment(), trainingDto.getNameOfTraining(), distanceDto.getDistance(), warmUpDto.getExercises(), warmUpDto.getTrot(),
                    trainingDto.getDateOfTrain(), trainingID, runsDto.getPause(), durationInputDto.getUnit(), durationInputDto.getDurationOfRun(), runsDto.getRepetition(),
                    runsDto.getNumberOfRuns());
            return new ResponseEntity<>("training was updated", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllTrainingsAsAdmin")
    public ResponseEntity<?> getAllTrainingsAsAdmin() {
        try{
            List<Training> trainingListForAdmin = trainingService.getTrainingList();
            List<TrainingForAdminDto> trainingForAdminDtoList = new ArrayList<>();
            for (Training training : trainingListForAdmin) {
                trainingForAdminDtoList.add(trainingMapper.trainingForAdmin(training));
            }
            return new ResponseEntity<>(trainingForAdminDtoList, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('Coach')")
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllTrainingsAsCoach")
    public ResponseEntity<?> getAllTrainingsAsCoach() {
        try{
            List<Training> trainingListForCoach = trainingService.getTrainingList();
            List<TrainingForCoachDto> trainingForCoachDtoList = new ArrayList<>();
            for (Training training : trainingListForCoach) {
                trainingForCoachDtoList.add(trainingMapper.trainingForCoachDto(training));
            }
            return new ResponseEntity<>(trainingForCoachDtoList, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getTrainingById")
    public ResponseEntity<?> getTrainingById(@RequestParam Long trainingId) {
        try {
            Training training = trainingService.getTrainingById(trainingId);
            TrainingForUserDto trainingForUserDto = trainingMapper.trainingForUserDto(training);
            return new ResponseEntity<>(trainingForUserDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint pro získání tréninkového kalendáře
    @GetMapping("/getUserCalendar")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<Days, List<Training>> getTrainingSchedule(@PathVariable Long userId) {
        return trainingService.getTrainingScheduleForUser(userId);
    }
}
// TODO udelat save delete metody v servisu a v controleru pridat adjustnumberoftrainings, udelat week service,

