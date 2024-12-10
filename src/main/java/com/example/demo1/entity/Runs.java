package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Runs {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long runsId;

    @OneToMany(mappedBy = "runs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Distance> distances;

    @OneToMany(mappedBy = "runs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DurationInput> durations;

  /*  private Long durationOfRun;

    private String unit;  seconds minutes hours */

    private Long pause;

    private Integer numberOfRuns;

    @OneToOne
    @JoinColumn(name = "training_id")
    private Training training;

    private Integer repetition;  // of runs (200m 200m 200m) * 3

    //TODO Udělat logiku na durationOfRun sekundy minuty hodiny
    //TODO pause bude v minutách a sekundách


}


