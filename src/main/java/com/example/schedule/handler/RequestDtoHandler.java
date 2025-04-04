package com.example.schedule.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RequestDtoHandler {

    //이거 기억 안나서 예전 코드 보고 함.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerRequestDto(MethodArgumentNotValidException ex){
        Map<String, String> map = new HashMap<>();
        // e 가 많은 정보를 갖고 있을 수 있어서 for문으로 받아옴
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            map.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

}
