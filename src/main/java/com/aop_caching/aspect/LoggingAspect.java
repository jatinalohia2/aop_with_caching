package com.aop_caching.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    // return type  , package name  , method argument

//    @Before("execution(* placeOrder(..))")
//@Before("execution(* com.aop_caching.service.OrderService.*(..))")
@Before("execution(* com.aop_caching.service.*.*(..))")
public void beforeMethodCall(JoinPoint joinPoint){
        log.info("Order Id for this method {} with arg {}" ,
                joinPoint.getSignature().getName() , joinPoint.getArgs());
    }

@After("execution(* com.aop_caching.service.*.*(..))")
public void AfterMethodCall(JoinPoint joinPoint){
        log.info("After Order Id for this method {} with arg {}" ,
            joinPoint.getSignature().getName() , joinPoint.getArgs());
}


    @Before("within(com.aop_caching.service.*)")
    public void beforeMethodCallWithin(JoinPoint joinPoint){
        log.info("Order Id for this method within {} with arg {}" ,
                joinPoint.getSignature().getName() , joinPoint.getArgs());
    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionMethodCall(JoinPoint joinPoint){
        log.info("Order Id for this method beforeTransactionMethodCall {} with arg {}" ,
                joinPoint.getSignature().getName() , joinPoint.getArgs());
    }


}
