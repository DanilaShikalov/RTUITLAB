package itlab.demo.exception;

import java.text.MessageFormat;

public class WarehouseNotProductException extends RuntimeException {
    public WarehouseNotProductException(Long id) {
        super(MessageFormat.format("Product with id = {0} not found!", id));
    }
}
