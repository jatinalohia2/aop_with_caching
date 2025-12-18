package com.aop_caching.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {

    @Pointcut("execution(* com.aop_caching.service.*.*(..))")
    public void orderServicePointCut(){

    }

    @Around(value = "orderServicePointCut()")
    public Object validateOrder(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();

       Long orderId = (Long) args[0];

       if (orderId > 0){
           proceedingJoinPoint.proceed();
       }
       return "Can't return negative OrderId";
    }

    @Around(value = "orderServicePointCut()")
    public void checkTimeDiff(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        long diff = end - start;

        log.info("method name {} and time it takes {}"
                , proceedingJoinPoint.getSignature().getName() , diff);
    }
}
