package com.example.schedule.repository;

import com.example.schedule.dto.LoginRequestDto;
import com.example.schedule.entity.User;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {//JpaRepository<"@Entity 클래스", "@Id 데이터 타입"
    default User findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 유저입니다."));
    }

    Optional<User> findByEmail(String email);

    default Long findIdByEmailAndPasswordOrElseThrow(@Valid LoginRequestDto requestDto){
        User user = findByEmail(requestDto.getEmail()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"존재하지 않는 이메일 입니다."));

        if(!user.getPassword().equals(requestDto.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호가 일치하지 않습니다.");
        }
        return user.getId();
    }

}
