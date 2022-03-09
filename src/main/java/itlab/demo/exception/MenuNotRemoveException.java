package itlab.demo.exception;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class MenuNotRemoveException extends RuntimeException {
    public MenuNotRemoveException(List<Long> ids) {
        super(MessageFormat.format("Это блюдо уже заказано в {0} заказах!", ids.stream().distinct().collect(Collectors.toList())));
    }
}
