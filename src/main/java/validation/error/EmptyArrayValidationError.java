package validation.error;


public class EmptyArrayValidationError extends ValidationError {
    public EmptyArrayValidationError(String path) {
        super(path);
    }

    @Override
    public String toString() {
        return path + ", Unable to compare arrays (one of the arrays has 0 elements)";
    }
}

