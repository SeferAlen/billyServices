package com.billy.billyServices.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Aspect for before method execution
 */
@Aspect
@Configuration
public class BeforeAspect {

    private final Logger logger = LoggerFactory.getLogger(BeforeAspect.class);

    /**
     * Login endpoint for user login and token generation
     *
     * @param joinPoint {@link JoinPoint} the jointPoint
     */
    //@Before("execution(* com.billy.billyServices.api.*.*(..))")
    private void before(final JoinPoint joinPoint) {

        // implementation
    }
}
