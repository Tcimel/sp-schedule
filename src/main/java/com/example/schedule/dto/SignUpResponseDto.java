package com.example.schedule.dto;

public class SignUpResponseDto {

    private final String username;

    private final String email;

    public SignUpResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
