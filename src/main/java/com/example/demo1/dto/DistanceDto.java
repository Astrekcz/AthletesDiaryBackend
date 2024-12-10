package com.example.demo1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DistanceDto {

    private Long distance_id;

    private String distance;


}
