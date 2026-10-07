package tw.brad.spring5.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.brad.spring5.exception.JwtAuthException;

import java.util.Map;

@RestControllerAdvice       // 攔截器
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtAuthException.class)
    public ResponseEntity<Map<String,Object>> handleJwtAuthException(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("success",false, "mesg", "權限被拒"));
    }

}
