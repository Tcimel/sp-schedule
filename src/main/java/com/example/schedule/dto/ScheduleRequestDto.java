package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    @NotBlank(message = "필수")
    private final Long id;

    private final String title;

    private final String content;

    public ScheduleRequestDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
