package com.example.demo1.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RunsDto {

    private Long runsId;

    private String unit;

    private Long durationOfRun;

    private Long pause;

    private Integer numberOfRuns;

    private Integer repetition;

}
