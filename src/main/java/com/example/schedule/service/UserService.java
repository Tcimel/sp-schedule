package com.example.schedule.service;

import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.dto.LoginRequestDto;
import com.example.schedule.dto.LoginResponseDto;
import com.example.schedule.dto.SignUpResponseDto;
import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import jakarta.validation.Valid;
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
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDto signUp(String name, String password, String email){

        // passwordEncoder 솔직히 처음 어떻게 시작해야할지 몰라서 사용 방법 지피티한테 한번 물어봤습니다.
        String encodedPassword = passwordEncoder.encdoe(password);

        //유저 데이터 저장
        User savedUser = userRepository.save(new User(name,encodedPassword,email));

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

        if(!passwordEncoder.matches(oldPw,findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }
//        if(!findUser.getPassword().equals(oldPw)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"기존 비밀번호가 일치하지 않습니다.");
//        }
        findUser.updatePassword(newPw);
    }

    public void deleteUser(Long id, String pw){
        User findUser = userRepository.findByIdOrElseThrow(id);
        if(!passwordEncoder.matches(pw,findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }
//        if(!findUser.getPassword().equals(pw)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호 불일치");
//        }
        userRepository.delete(findUser);
    }

    public LoginResponseDto login(@Valid LoginRequestDto requestDto) {

        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 이메일 입니다."));

        //암호화 된 코드 비교
        if(!passwordEncoder.matches(requestDto.getPassword(),user.getPassword()))
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }
        //입력받은 이메일과 비밀번호로 유저 아이디 조회
//        Long id = userRepository.findIdByEmailAndPasswordOrElseThrow(requestDto);

        return new LoginResponseDto(user.getId());
    }
}
