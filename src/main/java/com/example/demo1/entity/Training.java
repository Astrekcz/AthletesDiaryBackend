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
public class Training {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long trainingID;

    private LocalDate dateOfTrain;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private Days days;

    private String nameOfTraining;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @OneToOne(mappedBy = "training", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Runs runs;

    @OneToOne(mappedBy = "training", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private WarmUp warmUp;

}
