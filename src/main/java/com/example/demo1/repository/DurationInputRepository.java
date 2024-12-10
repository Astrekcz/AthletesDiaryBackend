package com.example.demo1.repository;

import com.example.demo1.entity.DurationInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DurationInputRepository extends JpaRepository<DurationInput, Long> {
    List<DurationInput> findByUserId(Long userId);
}
