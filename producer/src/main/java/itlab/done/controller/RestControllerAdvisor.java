package itlab.done.controller;

import itlab.done.exception.HistoryNotFoundException;
import itlab.done.exception.ProductAmountZeroException;
import itlab.done.exception.ProductNotFoundException;
import itlab.done.exception.ProductNotRemoveException;
import itlab.done.model.DemoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {
    @ExceptionHandler(value = {ProductNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionProductFound(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {ProductNotRemoveException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionProductRemove(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {HistoryNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionHistoryFound(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {ProductAmountZeroException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionProductZero(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }
}
