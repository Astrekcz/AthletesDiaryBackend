package com.example.demo.repository;

import com.example.demo.entity.Runs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunsRepository extends JpaRepository<Runs, Long> {
    List<Runs> findByUserId(Long userId);
}
