package com.white.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author Administrator
 */
@Aspect
@Component
public class LoggerPrint {

    private Logger logger = Logger.getLogger(String.valueOf(LoggerPrint.class));

    /**
     * 定义切面
     */
    @Pointcut(value = "execution( * com.white.controller.*.*(..))")
    public void myPointcut(){

    }

    /**
     *
     */
    @Around("myPointcut()")
    public Object myLogger(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        logger.info("调用前");
        logger.info(className+"："+methodName);
        logger.info("传递的参数："+new ObjectMapper().writeValueAsString(args));

        Object obj = pjp.proceed();

        logger.info("调用后");
        logger.info(className+"："+methodName);
        logger.info("返回值："+new ObjectMapper().writeValueAsString(obj));


        return obj;
    }

}
