package itlab.demo.exception;

import java.text.MessageFormat;

public class EmployeesNotFoundException extends RuntimeException {
    public EmployeesNotFoundException(Long id) {
        super(MessageFormat.format("Employees with id = {0} not found!", id));
    }
}
