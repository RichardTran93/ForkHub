package com.github.mobile.aspects.Lazy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;

/**
 * Created by henrikmnm on 05/03/2017.
 */


@Aspect
public class LazyAspect {

    /*
    private static final String POINTCUT_METHOD =
        "execution(@com.github.mobile.core * *(..))";
     */


    private static final String POINTCUT_CONSTRUCTOR =
            "call(@com.github.mobile.core *.new(..))";

    /*@Pointcut(POINTCUT_METHOD)
    public void anyMethodExecution(){}
    */

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void anyConstructorCalled(){}

// Handle the lazy instatiation of any object.
    @Before("anyConstructorCalled()")
    public void handleLazyness(JoinPoint joinPoint) throws Throwable{

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getName();

        LazyLogger.logLazyness(className, buildLogMessage(methodName));
    }

    private static String buildLogMessage(String methodName){
        StringBuilder message = new StringBuilder();

        message.append("Package: core --->");
        message.append(methodName);
        message.append(" --->");
        message.append("Instantiated");

        return message.toString();
    }



}
