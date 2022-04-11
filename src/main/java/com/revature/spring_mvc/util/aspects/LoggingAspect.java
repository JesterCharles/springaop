package com.revature.spring_mvc.util.aspects;

import net.bytebuddy.asm.Advice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LogManager.getLogger();

    @Pointcut("within(com.revature..*)")
    public void pc(){};

    @Before("pc()")
    public void logMethodStart(JoinPoint jp){
        String methodSig = extractMethodSignature(jp);
        String methodArgs = Arrays.toString(jp.getArgs());
        logger.info("{} invoked at {} with provided arguments: {}", methodSig, LocalDateTime.now().toString(), methodArgs);
    }

    @AfterReturning(pointcut = "pc()", returning = "returnedObject") // returnedObject is arbitrary name for the method sig
    public void logMethodReturn(JoinPoint jp, Object returnedObject){
        String methodSig = extractMethodSignature(jp);
        logger.info("{} succesfully returned at {} with value: {}", methodSig, LocalDateTime.now().toString(), returnedObject);
    }

    @AfterThrowing(pointcut = "pc()", throwing = "thrown") // thrown is arbitrary name for method sig
    public void logMethodException(JoinPoint jp, Throwable thrown){
        String methodSig = extractMethodSignature(jp);
        String exceptionName = thrown.getClass().getName();
        logger.info("{} was thrown in method {} at {} with messaged: {}", exceptionName, methodSig, LocalDateTime.now().toString(), thrown.getMessage());
    }


    // Means to extract  the method signature for the above methods
    private String extractMethodSignature(JoinPoint jp){
        return jp.getTarget().getClass().toString() + "#" + jp.getSignature().getName();
    }

}
