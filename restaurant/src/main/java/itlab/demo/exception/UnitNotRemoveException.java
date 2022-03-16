package itlab.demo.exception;

public class UnitNotRemoveException extends RuntimeException {
    public UnitNotRemoveException() {
        super("Эти размерности используются в блюдах!");
    }
}
