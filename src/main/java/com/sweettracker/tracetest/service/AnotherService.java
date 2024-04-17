package com.sweettracker.tracetest.service;

import com.sweettracker.tracetest.aop.Traced;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnotherService {

    @Traced
    public String getData(String errorYn){
        log.info("[getData] call");
        if(errorYn.equals("Y")){
            throw new RuntimeException("에러 발생");
        }
        return "hello trace";
    }
}

