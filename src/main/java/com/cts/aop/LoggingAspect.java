package com.cognizant.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
    @Before(" execution(* com.cognizant.*.*.*(..))")
    public void beforeAdvice(JoinPoint point) {
        logger.info("INFO:"+point.getSignature().getName()+" method called");
    }
    @After(" execution(* com.cognizant.*.*.*(..))")
    public void afterAdvice(JoinPoint point) {
        logger.info("INFO:"+point.getSignature().getName()+" method executed successfully");
    }
    @AfterReturning(pointcut="execution(* com.cognizant.*.*.*(..))",returning="result")
    public void afterReturning(JoinPoint point,Object result) {
        logger.debug("DEBUG:"+point.getSignature().getName()+" Return Value:"+result);
    }
    @AfterThrowing(pointcut="execution(* com.cognizant.*.*.*(..))",throwing="error")
    public void afterThrowing(JoinPoint point,Throwable error) {
        logger.error("Error:"+point.getSignature().getName()+" threw exception :"+error);
    }
}
