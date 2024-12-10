package com.example.demo.repository;

import com.example.demo.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {
    List<Distance> findAllByUser_Id(Long userId);
}
