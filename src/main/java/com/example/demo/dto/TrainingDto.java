package com.example.demo.dto;


import com.example.demo.entity.Days;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Builder
@Getter
@Setter
public class TrainingDto {

    private Long trainingID;

    private LocalDate dateOfTrain;

    private Days days;

    private String nameOfTraining;

    private String comment;

    private String userlastname;

    private String userfirstname;


}
