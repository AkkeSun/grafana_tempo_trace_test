package com.sweettracker.tracetest.service;

import com.sweettracker.tracetest.aop.Traced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ApiCallUtil {

    @Traced
    public String getApiResponse(String errorYn) {
        log.info("[getApiResponse] call");
        Mono<String> response = WebClient.builder().baseUrl("http://localhost:8088")
            .build().get().uri("/api/another?errorYn=" + errorYn)
            .retrieve().bodyToMono(String.class);
        return response.block();
    }
}
