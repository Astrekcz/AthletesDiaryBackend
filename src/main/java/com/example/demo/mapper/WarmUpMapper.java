package com.example.demo.mapper;


import com.example.demo.dto.WarmUpDto;
import com.example.demo.entity.WarmUp;
import org.springframework.stereotype.Component;

@Component
public class WarmUpMapper {
    public WarmUpDto warmUpDtoToWarmUpEntity(WarmUp warmUp) {
        WarmUpDto warmUpDto = WarmUpDto.builder()
                .warmUpID(warmUp.getWarmUpID())
                .trot(warmUp.getTrot())
                .exercises(warmUp.getExercises())
                .build();
        return warmUpDto;
    }

}
