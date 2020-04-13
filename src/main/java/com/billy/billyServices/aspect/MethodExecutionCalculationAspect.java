package com.billy.billyServices.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Aspect for method execution time calculation
 */
@Aspect
@Configuration
public class MethodExecutionCalculationAspect {

    private final Logger logger = LoggerFactory.getLogger(BeforeAspect.class);

    /**
     * Login endpoint for user login and token generation
     *
     * @param joinPoint {@link ProceedingJoinPoint} the jointPoint
     * @return {@link Object} the object on which method has been called
     * @throws {@link Throwable} the exception
     */
    @Around("com.billy.billyServices.aspect.CommonJoinPointConfig.trackTimeAnnotation()")
    private Object before(final ProceedingJoinPoint joinPoint) throws Throwable {
        final long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        logger.info("Method {} execution time: {}ms", joinPoint, System.currentTimeMillis() - startTime );
        return joinPoint.proceed();
    }
}
