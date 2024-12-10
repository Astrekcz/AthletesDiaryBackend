package com.example.demo1.mapper;

import com.example.demo1.entity.Runs;
import com.example.demo1.dto.RunsDto;
import org.springframework.stereotype.Component;

@Component
public class RunsMapper {

    public RunsDto RunsEntityToRunsDto(Runs runs) {
        RunsDto runsDto = RunsDto.builder()
                .runsId(runs.getRunsId())
                .pause(runs.getPause())
                .numberOfRuns(runs.getNumberOfRuns())
                .repetition(runs.getRepetition())
                .build();
        return runsDto;
    }

}
