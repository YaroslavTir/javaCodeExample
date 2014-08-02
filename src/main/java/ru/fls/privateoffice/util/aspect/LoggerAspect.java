package ru.fls.privateoffice.util.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: NKrivko
 * Date: 05.04.12
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
@Aspect
@Component
public class LoggerAspect {
    private static final Logger LOG = Logger.getLogger(LoggerAspect.class);

    @Pointcut("@annotation(ru.fls.privateoffice.util.annotation.EnableLogger)")
    public void methodWithEnableLoggerAnnotation() {}

    @Pointcut("execution(public * * (..))")
    public void anyOperation() {
    }
    @Pointcut("methodWithEnableLoggerAnnotation() && anyOperation()")
     public void enableLogger(){}

    @AfterThrowing(pointcut="enableLogger()", throwing="ex")
    public void log(Throwable ex ) {
       LOG.debug(ex.getMessage());
    }

}
