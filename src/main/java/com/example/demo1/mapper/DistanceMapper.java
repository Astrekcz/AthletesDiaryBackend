package com.example.demo1.mapper;

import com.example.demo1.entity.Distance;
import com.example.demo1.dto.DistanceDto;
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
