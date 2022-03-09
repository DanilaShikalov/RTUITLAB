package itlab.demo.exception;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseNotRemoveException extends RuntimeException {
    public WarehouseNotRemoveException(List<Long> ids) {
        super(MessageFormat.format("Этот ингредиент используется в {0} блюдах!", ids.stream().distinct().collect(Collectors.toList())));
    }
}
