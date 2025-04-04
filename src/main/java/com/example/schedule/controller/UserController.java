package com.example.schedule.controller;

import com.example.schedule.common.Const;
import com.example.schedule.dto.*;
import com.example.schedule.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto responseDto = userService.signUp(requestDto.getUsername(),requestDto.getPassword(),requestDto.getEmail());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto responseDto = userService.findById(id);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePasswordRequestDto requestDto
            ){
        userService.updatePassword(id,requestDto.getOldPw(),requestDto.getNewPw());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //쿠키 처리 방법
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

    //세션 처리 방법
    @PostMapping("/session-login")
    public ResponseEntity<String> Login(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpServletRequest request
    ){
        LoginResponseDto responseDto = userService.login(requestDto);
        Long userId = responseDto.getId();

        /*if(userId == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 유저입니다.");
            //어차피 서비스에서 로그인 처리로 유저 조회할때 Throw Else 하니까 주석 처리
        }*/

        //로그인 성공
        //getSession(true) : default, 세션 없으면 생성
        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findById(userId);

        // 세션에 로그인 회원 정보 저장
        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>("로그인 성공",HttpStatus.OK);
    }

    //Get Delete 역등성 때문에 리퀘스트 바디로 받아오는 방식 권장하지 않음.


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody String password){
        userService.deleteUser(id,password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
