package com.example.profile_project.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAOP {

    @Before("execution(* com.example.profile_project.service.PersonalInfoService.*(..))")
    public void beforeService() {
        log.info("[API - LOG] 요청 확인 완료");
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.profile_project.service.PersonalInfoService.*(..))",
            throwing = "exception")
    public void logServiceException(@NonNull JoinPoint joinPoint, Throwable exception) {
        log.error("[API - LOG] 🚨실행 중 에러 발생 🚨\n {}",
                joinPoint.getSignature().toShortString(), exception);
    }
}
