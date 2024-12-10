package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DurationInput {

    @Id
    @GeneratedValue
    private Long DurId;

    private Long DurationOfRun;

    private String Unit;

    @ManyToOne
    @JoinColumn(name = "runs_id")
    private Runs runs;
}
