package itlab.done.exception;

import java.text.MessageFormat;

public class ProductNotRemoveException extends RuntimeException {
    public ProductNotRemoveException(Long id) {
        super(MessageFormat.format("Этот продукт {0} находится в истории, нежелательное удаление", id));
    }
}
