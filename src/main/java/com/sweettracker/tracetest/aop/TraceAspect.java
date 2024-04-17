package com.sweettracker.tracetest.aop;

import io.opentracing.Span;
import io.opentracing.Tracer;
import java.util.UUID;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAspect {

    private final Tracer tracer;

    @Autowired
    public TraceAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Around("@annotation(com.sweettracker.tracetest.aop.Traced)")
    public Object trace (ProceedingJoinPoint joinPoint) throws Throwable{
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Span span = tracer.buildSpan(className + "." + methodName).start(); // 추적시작
        MDC.put("SPAN_ID", span.context().toSpanId());
        try {
            return joinPoint.proceed();
        } finally {
            span.finish(); // 추적 종료
        }
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object controllerTrace (ProceedingJoinPoint joinPoint) throws Throwable{
        MDC.put("TRACE_ID", tracer.activeSpan().context().toTraceId());
        MDC.put("SPAN_ID", tracer.activeSpan().context().toSpanId());
        return joinPoint.proceed();
    }
}