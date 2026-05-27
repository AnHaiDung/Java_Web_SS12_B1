package com.demo.ss12_b1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.demo.ss12_b1.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before - Method: " + joinPoint.getSignature().getName()
                + ", args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "execution(* com.demo.ss12_b1.service.impl.*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("AfterReturning - Method: " + joinPoint.getSignature().getName()
                + ", result: " + result);
    }

    @Around("execution(* com.demo.ss12_b1.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        System.out.println("Around - Method: " + joinPoint.getSignature().getName()
                + ", time: " + (endTime - startTime) + " ms");

        return result;
    }
}
