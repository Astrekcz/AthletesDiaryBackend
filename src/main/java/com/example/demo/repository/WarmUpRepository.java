package com.example.demo.repository;

import com.example.demo.entity.WarmUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarmUpRepository extends JpaRepository<WarmUp, Long> {
    List<WarmUp> findAllByUser_Id(Long userId);
}
