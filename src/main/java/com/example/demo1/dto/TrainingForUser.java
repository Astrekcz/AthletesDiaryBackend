package com.example.demo1.dto;
import com.example.demo1.entity.Days;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class TrainingForUser {

    private Long trainingID;

    private Long warmUpID;

    private String trot;

    private Days days;

    private String exercises;

    private LocalDate dateOfTrain;

    private String nameOfTraining;

    private String comment;

    //Runs
    private Long runsId;

    private Long durationOfRun;

    private String unit;

    private Long pause;

    private Integer repetition;

    private Integer numberOfRuns;

}