package itlab.demo.exception;

import java.text.MessageFormat;

public class PositionsNotFoundException extends RuntimeException {
    public PositionsNotFoundException(Long id) {
        super(MessageFormat.format("Position with id = {0} not found!", id));
    }
}
