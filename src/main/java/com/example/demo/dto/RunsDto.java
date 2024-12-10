package com.example.demo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

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
