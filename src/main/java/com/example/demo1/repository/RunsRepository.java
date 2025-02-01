package com.example.demo1.repository;

import com.example.demo1.entity.Runs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunsRepository extends JpaRepository<Runs, Long> {
    List<Runs> findByTraining_TrainingID(Long trainingID);
}
