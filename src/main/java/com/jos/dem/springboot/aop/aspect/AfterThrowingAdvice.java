package com.jos.dem.springboot.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import com.jos.dem.springboot.aop.exception.DemoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class AfterThrowingAdvice {

  private Logger log = LoggerFactory.getLogger(this.getClass());

//Lösung 1: 
//   @AfterThrowing(pointcut = "execution(* com.jos.dem.springboot.aop.service..**.*(..))", throwing = "ex")
//   public void doRecoveryActions(RuntimeException ex){
//     log.info("Wrapping exception: " + ex);
//     // throw new DemoException(ex.getMessage(), ex);
//   }

// Lösung 2:
    @Around("execution(* com.jos.dem.springboot.aop.service..*.*(..))")
public void exceptionHandlerWithReturnType(ProceedingJoinPoint joinPoint) throws Throwable{
    Object obj1;
    try {
        obj1 = joinPoint.proceed();
    } catch(Exception ex) {
        if(ex instanceof RuntimeException){
            System.out.print(ex.getMessage() + " wrapped by aspect, because it is a runtime exception");
        }
        
    }
}

}
