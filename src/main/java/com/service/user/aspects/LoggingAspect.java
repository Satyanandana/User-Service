package com.service.user.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.service.user.controllers.UserController.getUsers(..))")
    public void controllerMethods(){};

    @Around("controllerMethods()")
    public Object logControllerMethods(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("Calling : " + proceedingJoinPoint.getSignature().toString());
        logger.info("With arguments :  " + Arrays.toString(proceedingJoinPoint.getArgs()));
        Object obj = null;
        try {
            obj =  proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("Handled error in aop",throwable);
        }
        logger.info("completed the call to: " + proceedingJoinPoint.getSignature().toString());
        return obj;
    }

}
