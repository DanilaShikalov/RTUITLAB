package itlab.done.exception;

import java.text.MessageFormat;

public class ProductAmountZeroException extends RuntimeException {
    public ProductAmountZeroException(int id) {
        super(MessageFormat.format("Нельзя заказать больше чем {0}", id));
    }
}
