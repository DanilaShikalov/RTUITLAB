package itlab.demo.controller;

import itlab.demo.exception.*;
import itlab.demo.model.DemoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {
    @ExceptionHandler(value = {PositionsNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionPosition(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {EmployeesNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionEmployees(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {OrdersNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionOrders(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {MenuNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionMenu(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {MenuNotRemoveException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionMenuRemove(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {WarehouseNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionWarehouse(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {WarehouseAmountZeroException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionWarehouseZero(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {WarehouseNotRemoveException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionWarehouseRemove(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {UnitNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionUnit(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {UnitNotRemoveException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionUnitRemove(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {DishNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionDish(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }

    @ExceptionHandler(value = {IngredientNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DemoResponse<Void> handlePetHelperExceptionIngredient(RuntimeException ex) {
        return DemoResponse.error(ex.getMessage(), null);
    }
}
