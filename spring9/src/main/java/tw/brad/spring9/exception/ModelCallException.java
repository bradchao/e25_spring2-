package tw.brad.spring9.exception;

public class ModelCallException extends RuntimeException{
    public ModelCallException(Throwable cause){
        super("Model call failed", cause);
    }
}
