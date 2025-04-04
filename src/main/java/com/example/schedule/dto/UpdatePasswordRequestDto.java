package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {
    @NotBlank(message = "필수")
    private final String oldPw;

    @NotBlank(message = "필수")
    private final String newPw;

    public UpdatePasswordRequestDto(String oldPw, String newPw) {
        this.oldPw = oldPw;
        this.newPw = newPw;
    }
}
