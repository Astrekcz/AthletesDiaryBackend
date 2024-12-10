package com.example.demo1.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DurationInputDto {

    private Long DurId;

    private Long DurationOfRun;

    private String Unit;
}
