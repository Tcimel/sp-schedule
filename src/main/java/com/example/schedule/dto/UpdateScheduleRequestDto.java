package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    @NotBlank(message = "필수")
    private final String password;

    private final String title;

    private final String content;

    public UpdateScheduleRequestDto(String password, String title, String content) {
        this.password = password;
        this.title = title;
        this.content = content;
    }
}
