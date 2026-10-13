package tw.brad.spring9.exception;

public class ModelOutputException extends RuntimeException {
    public ModelOutputException() {
        super("Model output 無法滿足 app 的規範");
    }
}
