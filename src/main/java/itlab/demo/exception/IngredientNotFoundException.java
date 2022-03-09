package itlab.demo.exception;

import java.text.MessageFormat;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Long id) {
        super(MessageFormat.format("Ingredient with id = {0} not found!", id));
    }
}
