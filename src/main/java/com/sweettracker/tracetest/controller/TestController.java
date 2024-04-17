package com.sweettracker.tracetest.controller;

import com.sweettracker.tracetest.service.AnotherService;
import com.sweettracker.tracetest.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final MyService myService;
    private final AnotherService anotherService;

    @GetMapping("/test")
    public String test(String errorYn) {
        log.info("[test api] call");
        return myService.getTestData(errorYn);
    }

    @GetMapping("/api/another")
    public String anotherApiCall (String errorYn) {
        log.info("[another api] call");
        return anotherService.getData(errorYn);
    }
}
