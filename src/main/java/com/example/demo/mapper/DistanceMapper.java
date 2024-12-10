package com.example.demo.mapper;

import com.example.demo.dto.DistanceDto;
import com.example.demo.entity.Distance;
import org.springframework.stereotype.Component;

@Component
public class DistanceMapper {
    DistanceDto DistanceDtoToEntity(Distance distance) {


        DistanceDto distanceDto = DistanceDto.builder()
                .distance_id(distance.getDistance_id())
                .distance(distance.getDistance())
                .build();
        return distanceDto;
    }
}
