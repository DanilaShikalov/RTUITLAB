package itlab.demo.exception;

import java.text.MessageFormat;

public class WarehouseAmountZeroException extends RuntimeException {
    public WarehouseAmountZeroException(String ingredient) {
        super(MessageFormat.format("Ингредиент {0} not found!", ingredient));
    }
}
