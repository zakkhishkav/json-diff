package validation.error;

public class NullValueValidationError extends  ValidationError{

    public NullValueValidationError(String path ){
        super(path);
    }

    @Override
    public String toString() {
        return path + ", Unable to do comparison due to null value";
    }
}
