package tw.brad.spring5.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tw.brad.spring5.exception.JwtAuthException;
import tw.brad.spring5.util.JwtToken;

@Aspect
@Component
public class JwtAspect {
    @Around("@annotation(tw.brad.spring5.annotation.CheckJwt)")
    public Object checkJwt(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes =
        (ServletRequestAttributes)(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = attributes.getRequest();

        request.getSession();
        String urIp = request.getRemoteAddr();
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null){
            throw new JwtAuthException("no token");
        }

        if (!authHeader.startsWith("Bearer ")){
            throw new JwtAuthException("token format error");
        }

        try {
            String token = authHeader.split(" ")[1];
            String data = JwtToken.parseToken(token);
            if (data != null){
                System.out.println(data);
            }else{
                throw new JwtAuthException("token data error");
            }
        }catch (Exception e){
            throw new JwtAuthException("token parse error");
        }


        return joinPoint.proceed();
    }
}
