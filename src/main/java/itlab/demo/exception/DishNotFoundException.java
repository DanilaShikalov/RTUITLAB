package itlab.demo.exception;

import java.text.MessageFormat;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long id) {
        super(MessageFormat.format("Dish with id = {0} not found!", id));
    }
}
