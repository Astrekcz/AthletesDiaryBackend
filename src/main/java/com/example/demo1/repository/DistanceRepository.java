package com.example.demo1.repository;

import com.example.demo1.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {
    List<Distance> findAllByUser_Id(Long userId);
}
