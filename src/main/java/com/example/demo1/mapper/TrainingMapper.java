package com.example.demo1.mapper;

import com.example.demo1.dto.TrainingForAdminDto;
import com.example.demo1.dto.TrainingForCoachDto;
import com.example.demo1.entity.DurationInput;
import com.example.demo1.entity.Runs;
import com.example.demo1.entity.Training;
import com.example.demo1.dto.TrainingDto;
import com.example.demo1.dto.TrainingForUserDto;
import com.example.demo1.entity.WarmUp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {
    public TrainingDto TrainingEntityToDto(Training training) {
        TrainingDto trainingDto = TrainingDto.builder()
                .trainingID(training.getTrainingID())
                .nameOfTraining(training.getNameOfTraining())
                .dateOfTrain(training.getDateOfTrain())
                .comment(training.getComment())
                .userfirstname(training.getUser().getFirstname())
                .userlastname(training.getUser().getLastname())
                .build();
    return trainingDto;
    }
    public TrainingForUserDto trainingForUserDto(Training training) {
        WarmUp warmUp = training.getWarmUp();
        Runs runs = training.getRuns();
        DurationInput durationInput = new DurationInput();

        TrainingForUserDto trainingForUserDto = TrainingForUserDto.builder()
                .trainingID(training.getTrainingID())
                .runsId(runs.getRunsId())
                .warmUpID(warmUp.getWarmUpID())
                .trot(warmUp.getTrot())
                .nameOfTraining(training.getNameOfTraining())
                .dateOfTrain(training.getDateOfTrain())
                .comment(training.getComment())
                .exercises(warmUp.getExercises())
                .dateOfTrain(training.getDateOfTrain())
                .nameOfTraining(training.getNameOfTraining())
                .comment(training.getComment())
                .durationOfRun((durationInput.getDurationOfRun()))
                .pause(runs.getPause())
                .repetition(runs.getRepetition())
                .build();


        return trainingForUserDto;
    }
    public TrainingForCoachDto trainingForCoachDto(Training training) {

        WarmUp warmUp = training.getWarmUp();
        Runs runs = training.getRuns();
        DurationInput durationInput = new DurationInput();

        TrainingForCoachDto trainingForUserDto = TrainingForCoachDto.builder()
                .trainingID(training.getTrainingID())
                .runsId(runs.getRunsId())
                .warmUpID(warmUp.getWarmUpID())
                .trot(warmUp.getTrot())
                .nameOfTraining(training.getNameOfTraining())
                .dateOfTrain(training.getDateOfTrain())
                .comment(training.getComment())
                .exercises(warmUp.getExercises())
                .dateOfTrain(training.getDateOfTrain())
                .nameOfTraining(training.getNameOfTraining())
                .comment(training.getComment())
                .durationOfRun(durationInput.getDurationOfRun())
                .pause(runs.getPause())
                .repetition(runs.getRepetition())
                .days(training.getDays())
                .build();

        return trainingForUserDto;

    }
    public TrainingForAdminDto trainingForAdmin(Training training){

        WarmUp warmUp = training.getWarmUp();
        Runs runs = training.getRuns();
        DurationInput durationInput = new DurationInput();


        TrainingForAdminDto trainingForAdminDto = TrainingForAdminDto.builder()
                .trainingID(training.getTrainingID())
                .runsId(runs.getRunsId())
                .warmUpID(warmUp.getWarmUpID())
                .trot(warmUp.getTrot())
                .nameOfTraining(training.getNameOfTraining())
                .dateOfTrain(training.getDateOfTrain())
                .comment(training.getComment())
                .exercises(warmUp.getExercises())
                .dateOfTrain(training.getDateOfTrain())
                .nameOfTraining(training.getNameOfTraining())
                .comment(training.getComment())
                .durationOfRun(durationInput.getDurationOfRun())
                .pause(runs.getPause())
                .repetition(runs.getRepetition())
                .days(training.getDays())
                .build();

        return trainingForAdminDto;

        //budou jenom 1 training mapper ktery bude mapovat jenom zakladni data o treninku.
        //ostatni tridy warmUp atd se udelali jednotlive mappery

    }

}
