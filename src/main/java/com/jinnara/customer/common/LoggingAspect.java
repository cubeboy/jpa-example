package com.jinnara.customer.common;

import com.jinnara.customer.domain.IllegalUsernameException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Before("@annotation(Loggable)")
  public void beforeMethodCall(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    Object[] args = joinPoint.getArgs();
    logger.info("Method {} is called with params : {}", methodName, args);
  }

  @AfterReturning(pointcut = "@annotation(Loggable)", returning = "result")
  public void afterMethodCall(Object result) {
    logger.info("Method returned : {}", result);
  }

  @AfterThrowing(pointcut = "@annotation(Loggable)", throwing = "exception")
  public void exceptionRaised(IllegalUsernameException exception) {
    logger.error("Illegal Username Exception raised : {}", exception.getMessage());
  }
}
