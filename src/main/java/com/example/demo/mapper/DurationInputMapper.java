package com.example.demo.mapper;

import com.example.demo.dto.DurationInputDto;
import com.example.demo.entity.DurationInput;
import org.springframework.stereotype.Component;

@Component
public class DurationInputMapper {
    public DurationInputDto DurationEntityToDurationInputDto(DurationInput duration) {

        DurationInputDto durationInputDto = DurationInputDto.builder()
                .DurId(duration.getDurId())
                .DurationOfRun(duration.getDurationOfRun())
                .Unit(duration.getUnit())
                .build();
        return durationInputDto;
    }
}
