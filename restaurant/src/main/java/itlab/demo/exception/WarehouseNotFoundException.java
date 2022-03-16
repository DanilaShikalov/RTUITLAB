package itlab.demo.exception;

import java.text.MessageFormat;

public class WarehouseNotFoundException extends RuntimeException {
    public WarehouseNotFoundException(Long id) {
        super(MessageFormat.format("Warehouse with id = {0} not found!", id));
    }
}
