package com.example.demo.mapper;

import com.example.demo.dto.WeekDto;
import com.example.demo.entity.Week;
import org.springframework.stereotype.Component;

@Component
public class WeekMapper {
    public WeekDto EntityToDto(Week week) {
        WeekDto weekDto = WeekDto.builder()
                .weekNumber(week.getWeekNumber())
                .startOfWeek(week.getStartOfWeek())
                .endOfWeek(week.getEndOfWeek())
                .weekId(week.getWMid())
                .build();
        return weekDto;
    }

}
