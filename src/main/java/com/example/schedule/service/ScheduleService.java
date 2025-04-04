package com.example.schedule.service;

import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.UpdateScheduleRequestDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.entity.User;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    public ScheduleResponseDto save(Long userId, String title, String content){
        User findUser = userRepository.findByIdOrElseThrow(userId);

        Schedule schedule = new Schedule(findUser,title,content);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule);
    }

    public List<ScheduleResponseDto> findAll(){
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto) // 정적 메세드를 만든 이유는 클래스를 선언하지 않고 바로 사용하기 위해서
                .toList();
    }

    public ScheduleResponseDto findById(Long id){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(requestDto.getPassword(),findSchedule.getUser().getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }
//        if(!findSchedule.getUser().getPassword().equals(requestDto.getPassword())){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호 불일치");
//        }

        if(requestDto.getTitle()!=null){
            findSchedule.updateTitle(requestDto.getTitle());
        }
        if(requestDto.getContent()!=null){
            findSchedule.updateContent(requestDto.getContent());
        }

        return new ScheduleResponseDto(findSchedule);
    }

    public void delete(Long id, String password){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(password,findSchedule.getUser().getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }
//        if(!findSchedule.getUser().getPassword().equals(password)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호 불일치");
//        }

        scheduleRepository.delete(findSchedule);
    }
}
