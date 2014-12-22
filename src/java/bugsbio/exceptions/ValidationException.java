package bugsbio.exceptions;

public class ValidationException extends Exception {
    public final Object validationErrors;

    public ValidationException(Object validationErrors) {
        this.validationErrors = validationErrors;
    }
    public Object getValidationErrors() {
        return this.validationErrors;
    }
}
