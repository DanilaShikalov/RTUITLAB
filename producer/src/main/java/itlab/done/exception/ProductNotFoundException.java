package itlab.done.exception;

import java.text.MessageFormat;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(MessageFormat.format("Product with id = {0} not found!", id));
    }
}
