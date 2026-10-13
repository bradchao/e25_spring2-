package tw.brad.spring9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AiExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class})
    public ProblemDetail invalidInput(Exception exception){
        return problem(HttpStatus.BAD_REQUEST, "INVALID_INPUT","輸入內容無效", null);
    }

    @ExceptionHandler(ModelOutputException.class)
    public ProblemDetail invalidOutput(ModelOutputException exception){
        return problem(HttpStatus.BAD_GATEWAY, "AI_OUTPUT_INVALID",
                "Model 輸出格式有問題, 請重新描述", null);
    }


    private ProblemDetail problem(HttpStatus status, String code, String detail, Integer providerStatus) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setProperty("code", code);
        if (providerStatus != null){
            problem.setProperty("providerStatus", providerStatus);
        }
        return problem;
    }



}
