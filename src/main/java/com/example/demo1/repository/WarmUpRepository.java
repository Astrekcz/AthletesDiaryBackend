package com.example.demo1.repository;

import com.example.demo1.entity.WarmUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarmUpRepository extends JpaRepository<WarmUp, Long> {
    List<WarmUp> findAllByTraining_TrainingID(Long trainingID);
}
