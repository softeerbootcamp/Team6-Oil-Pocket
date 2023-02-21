package com.kaspi.backend.util.aspect;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Timed(value = "api request")
    @Around("execution(* com.kaspi.backend.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            log.info("request method:{}, uri:{}", request.getMethod(), request.getRequestURI());

            Object result = joinPoint.proceed();

            log.info("response 상태코드:{}, data: {}, request URI:{}", ((ResponseEntity) result).getStatusCode(), ((ResponseEntity) result).getBody(), request.getRequestURI());

            return result;
        } catch (IllegalArgumentException e) {

            log.error("Illegal argument {} in {}", joinPoint.getSignature(), joinPoint.getSourceLocation());
            throw e;
        } finally {
            log.info("api 요청 package: {} 요청시간: {} ms", joinPoint.getSignature(), System.currentTimeMillis() - start);
        }
    }
}
