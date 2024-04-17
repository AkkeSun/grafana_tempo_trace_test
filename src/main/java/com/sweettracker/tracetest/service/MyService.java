package com.sweettracker.tracetest.service;

import com.sweettracker.tracetest.aop.Traced;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyService {

    private final ApiCallUtil apiCallUtil;

    @Traced
    public String getTestData (String errorYn) {
        log.info("[getTestData] call");
        return apiCallUtil.getApiResponse(errorYn);
    }
}

