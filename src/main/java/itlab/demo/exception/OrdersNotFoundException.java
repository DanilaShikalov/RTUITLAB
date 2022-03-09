package itlab.demo.exception;

import java.text.MessageFormat;

public class OrdersNotFoundException extends RuntimeException {
    public OrdersNotFoundException(Long id) {
        super(MessageFormat.format("Order with id = {0} not found!", id));
    }
}
