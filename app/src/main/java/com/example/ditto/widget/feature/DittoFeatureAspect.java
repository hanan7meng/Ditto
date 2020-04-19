package com.example.ditto.widget.feature;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author menghaonan
 * @date 2020/06/30
 */
@Aspect
public class DittoFeatureAspect {

    @Pointcut("execution(@com.example.ditto.widget.feature.ThemeFeature * *(..))")
    public void setupFeature() {

    }

    @Around("setupFeature() && @annotation(featureAnnotation)")
    public void handle(ProceedingJoinPoint joinPoint, ThemeFeature featureAnnotation) throws Throwable {
        ThemeFeatureManager.setupFeature(featureAnnotation.which(), joinPoint);
    }

}
