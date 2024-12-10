package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long distance_id;

    @Column(columnDefinition = "TEXT")
    private String distance;

    @ManyToOne
    @JoinColumn(name = "runs_id")
    private Runs runs;


}