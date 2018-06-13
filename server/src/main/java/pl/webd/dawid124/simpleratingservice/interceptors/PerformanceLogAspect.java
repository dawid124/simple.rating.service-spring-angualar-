package pl.webd.dawid124.simpleratingservice.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@Aspect
public class PerformanceLogAspect {

    @Value("${interceptors.maxExecutingTime}")
    public int MAX_EXECUTING_TIME;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceClass(){}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Around("publicMethod() && serviceClass()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        if (executionTime > MAX_EXECUTING_TIME) {
            LOGGER.warn(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        }

        return proceed;
    }
}
