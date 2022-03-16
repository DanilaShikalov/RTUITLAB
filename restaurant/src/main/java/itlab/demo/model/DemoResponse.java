package itlab.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DemoResponse<T> {
    private static final Integer OK_CODE = 0;
    private static final String OK_MSG = "OK";

    private static final Integer ERROR_CODE = 1;
    private static final String ERROR_MSG = "ERROR";

    private Meta meta;

    private T dataBlock;

    public static DemoResponse<Void> ok() {
        return new DemoResponse<>(new Meta(OK_CODE, OK_MSG), null);
    }

    public static <T> DemoResponse<T> ok(T data) {
        return new DemoResponse<>(new Meta(OK_CODE, OK_MSG), data);
    }

    public static DemoResponse<Void> error() {
        return new DemoResponse<>(new Meta(ERROR_CODE, ERROR_MSG), null);
    }

    public static <T> DemoResponse<T> error(String message, T data) {
        return new DemoResponse<>(new Meta(ERROR_CODE, message), data);
    }
}
