package com.example.schedule.dto;

import com.example.schedule.entity.User;

import java.util.Optional;

public class UserResponseDto {

    private final User user;

    public UserResponseDto(User user) {
        this.user = user;
    }
}
