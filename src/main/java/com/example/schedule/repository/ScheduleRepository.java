package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 게시물 입니다."));
    }
}
