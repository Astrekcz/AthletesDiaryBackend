package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


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

    private Integer numberOfTrainings;

    private LocalDate startOfWeek;

    @OneToMany(mappedBy = "trainingID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Training> trainingList;

    private LocalDate endOfWeek;

    @Enumerated(EnumType.STRING)
    private Days days;

    //TODO vymyslet logiku na přiřazení trénink(jednotlivých dnů) do week class

   /* public void adjustNumberOfTrainings() {
        this.numberOfTrainings = trainingList.size();
    }*/


}
