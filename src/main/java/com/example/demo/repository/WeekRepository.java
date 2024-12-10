package com.example.demo.repository;

import com.example.demo.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {
    Optional<Week> findWeekById(Integer WMid);



}
