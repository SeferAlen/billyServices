package com.billy.billyServices.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Class for AOP configuration
 */
public class CommonJoinPointConfig {

    /**
     * Common method for all service layer methods
     */
    @Pointcut("execution(* com.billy.billyServices.service.*.*(..))")
    public void serviceLayerExecution() {
    }

    /**
     * Common method for all beans with name containing 'Jpa'
     */
    @Pointcut("bean(*Jpa*)")
    public void repositoryLayerExecution() {
    }

    /**
     * Common method for all methods with {@link TrackTime} annotation
     */
    @Pointcut("@annotation(com.billy.billyServices.aspect.TrackTime)")
    public void trackTimeAnnotation() {
    }
}
