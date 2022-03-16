package itlab.demo.exception;

import java.text.MessageFormat;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(Long id) {
        super(MessageFormat.format("Dish with id = {0} not found!", id));
    }
}
