package com.max.littlebank.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author Serov Maxim
 */
@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public String com.max.littlebank.controller.UserController.deleteById(Long))")
    public void beforeShowUsersAdvice() {
        System.out.println("beforeShowUsersAdvice: attempt update user");
    }

    @Around("execution(* com.max.littlebank.controller.*.*(..))")
    public Object aroundAllRepositoryAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();
        System.out.println("Begin of " + methodName);
        Object targetMethodResult = proceedingJoinPoint.proceed();
        System.out.println("End of " + methodName);
        return targetMethodResult;
    }
}

