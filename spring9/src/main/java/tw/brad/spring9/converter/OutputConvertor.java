package tw.brad.spring9.converter;

import jakarta.validation.Validator;
import org.springframework.ai.converter.BeanOutputConverter;

/*
     => .entity(Record.class)
 */
public class OutputConvertor<T> extends BeanOutputConverter<T> {
    private final Validator validator;

    public OutputConvertor(Class<T> type, Validator validator){
        super(type);
        this.validator = validator;
    }

    @Override
    public T convert(String text) {
        final T result;

        try {
            result = super.convert(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (result == null || !validator.validate(result).isEmpty() ){

        }



        return result;
    }
}
