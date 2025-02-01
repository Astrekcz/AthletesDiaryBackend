package com.example.demo1.repository;

import com.example.demo1.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {
    Optional<Week> findWeekByWMid(Integer WMid);



}
