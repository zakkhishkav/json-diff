package validation.error;

public class MissingFieldValidationError extends  ValidationError{

    private String fieldName;

    public MissingFieldValidationError(String path, String fieldName){
        super(path);
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return path + ", Missing field <" + fieldName + ">";
    }
}