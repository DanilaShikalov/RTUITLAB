package itlab.done.exception;

import java.text.MessageFormat;

public class HistoryNotFoundException extends RuntimeException {
    public HistoryNotFoundException(Long id) {
        super(MessageFormat.format("History with id = {0} not found!", id));
    }
}
