package com.example.demo1.service;

import com.example.demo1.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeeksService {
    private final WeekRepository weekRepository;

    /*public Week saveWeek(){

    }*/
}
