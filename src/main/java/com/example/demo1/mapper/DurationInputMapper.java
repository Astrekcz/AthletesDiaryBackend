package com.example.demo1.mapper;

import com.example.demo1.entity.DurationInput;
import com.example.demo1.dto.DurationInputDto;
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
