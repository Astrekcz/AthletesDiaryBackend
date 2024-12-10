package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Week {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long WMid;

    private Integer weekNumber;

    private LocalDate startOfWeek;

    private LocalDate endOfWeek;

    @Enumerated(EnumType.STRING)
    private Days days;

    //TODO vymyslet logiku na přiřazení trénink(jednotlivých dnů) do week class


}
