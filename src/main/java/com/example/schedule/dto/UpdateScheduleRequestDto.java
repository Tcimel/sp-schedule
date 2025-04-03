package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    private final String password;

    private final String title;

    private final String content;

    public UpdateScheduleRequestDto(String password, String title, String content) {
        this.password = password;
        this.title = title;
        this.content = content;
    }
}
