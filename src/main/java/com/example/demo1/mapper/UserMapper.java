package com.example.demo1.mapper;

import com.example.demo1.dto.UserDto;
import com.example.demo1.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto userEntityToUserDto(User user) {
        UserDto userGetUserDto = UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        return userGetUserDto;
    }

}
