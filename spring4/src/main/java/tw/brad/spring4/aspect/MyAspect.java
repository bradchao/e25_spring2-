package tw.brad.spring4.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    public MyAspect(){System.out.println("MyAspect()");}

    @Pointcut("execution(* tw.brad.spring4.controller.MyController.*(..))")
    public void doMyController(){}

    @Before("doMyController()")
    public void doBefore(){
        System.out.println("doBefore()");
    }

    @After("doMyController()")
    public void doAfter(){
        System.out.println("doAfter()");
    }

    @Around("doMyController()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("around1()");
        //Object obj = joinPoint.proceed();
        System.out.println("around2()");
        return null;
    }


}
