package com.example.demo1.dto;

import com.example.demo1.entity.Days;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Builder
@Setter
@Getter
public class WeekDto {
    private Long weekId;

    @NotNull
    private Integer weekNumber;
    @NotNull
    private LocalDate startOfWeek;
    @NotNull
    private LocalDate endOfWeek;
    @NotBlank
    private Days days;
}
