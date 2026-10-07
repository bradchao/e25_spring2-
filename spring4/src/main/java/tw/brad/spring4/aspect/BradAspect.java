package tw.brad.spring4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BradAspect {
    @Around("@annotation(tw.brad.spring4.annotation.BradAop)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("around111()");
        Object obj = joinPoint.proceed();
        System.out.println("around222()");
        return null;
    }
}
