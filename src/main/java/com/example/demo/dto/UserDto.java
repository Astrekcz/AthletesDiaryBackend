package com.example.demo.dto;

import com.example.demo.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;


}
