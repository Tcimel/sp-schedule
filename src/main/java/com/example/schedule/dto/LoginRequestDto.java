package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "필수")
    private final String email;

    @NotBlank(message = "필수")
    private final String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
