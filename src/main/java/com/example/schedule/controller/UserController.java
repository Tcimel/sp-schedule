package com.example.schedule.controller;

import com.example.schedule.dto.*;
import com.example.schedule.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto responseDto = userService.signUp(requestDto.getUsername(),requestDto.getPassword(),requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
           @Valid @RequestBody LoginRequestDto requestDto,
           HttpServletResponse response
    ){
        //로그인 유저 조회
        LoginResponseDto responseDto = userService.login(requestDto);

        //아이디 없을 경우
        if(responseDto.getId()==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 아이디 입니다.");
        }

        //로그인 성공 시 쿠키 생성 및 저장
        Cookie cookie = new Cookie("userId",String.valueOf(responseDto.getId()));
        response.addCookie(cookie);

        return new ResponseEntity<>("로그인 성공",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto responseDto = userService.findById(id);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordDto requestDto
            ){
        userService.updatePassword(id,requestDto.getOldPw(),requestDto.getNewPw());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
