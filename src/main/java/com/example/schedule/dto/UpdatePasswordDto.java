package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class UpdatePasswordDto {
    private final String oldPw;

    private final String newPw;

    public UpdatePasswordDto(String oldPw, String newPw) {
        this.oldPw = oldPw;
        this.newPw = newPw;
    }
}
