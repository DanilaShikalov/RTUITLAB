package itlab.demo.exception;

import java.text.MessageFormat;

public class UnitNotFoundException extends RuntimeException {
    public UnitNotFoundException(Long id) {
        super(MessageFormat.format("Unit with id = {0} not found!", id));
    }
}
