package com.github.mobile.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;


/**
 * Created by EpiK on 3/5/2017.
 */

@Aspect
public class RepositoryCommitAspect {

    private static final String POINTCUT_METHOD =
            "execution(@com.github.mobile.core.annotation.CheckNull * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.github.mobile.core.annotation.CheckNull *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithCheckNull() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedWithCheckNull() {}

    @Before("methodAnnotatedWithCheckNull() || constructorAnnotatedWithCheckNull()")
    public void validateNotNull(JoinPoint joinPoint) throws Exception {

        Signature signature = joinPoint.getSignature();
        MethodSignature mSignature = (MethodSignature) signature;
        String className = mSignature.getDeclaringTypeName();
        String methodName = mSignature.getName();
        String[] parameters = mSignature.getParameterNames();

        Object arg = joinPoint.getArgs()[0];
        if(arg == null) {
            String message = messageBuilder(className, methodName);

            throw new CustomException(message, "First argument to method is null");
        }
    }

    private static String messageBuilder(String className, String methodName){
        StringBuilder message = new StringBuilder();

        message.append("Package ---> core --->");
        message.append("Class ---> "+ className);
        message.append("--->");
        message.append("Method ---> "+ methodName);

        return message.toString();
    }
}
