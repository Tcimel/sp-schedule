package com.example.schedule.service;

import com.example.schedule.dto.SignUpResponseDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String name, String password, String email){

        //유저 데이터 저장
        User savedUser = userRepository.save(new User(name,password,email));

        return new SignUpResponseDto(savedUser.getUsername(),savedUser.getEmail());

    }

    public UserResponseDto findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 유저입니다.");
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser);

    }

    public void updatePassword(Long id,String oldPw, String newPw){
        User findUser = userRepository.findByIdOrElseThrow(id);

        if(!findUser.getPassword().equals(oldPw)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"기존 비밀번호가 일치하지 않습니다.");
        }
        findUser.updatePassword(newPw);
    }

    public void deleteUser(Long id, String pw){
        User findUser = userRepository.findByIdOrElseThrow(id);
        if(!findUser.getPassword().equals(pw)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호 불일치");
        }
        userRepository.delete(findUser);
    }

}
