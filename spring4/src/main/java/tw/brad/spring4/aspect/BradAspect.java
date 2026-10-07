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
        long start = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.printf("%s:%d\n", methodName, args.length);
        for (Object obj: args){
            System.out.println(obj);
        }
        args[1] = args[1].toString().toUpperCase();


        Object obj = joinPoint.proceed(args);


        System.out.println(System.currentTimeMillis() - start);
        return null;
    }
}
