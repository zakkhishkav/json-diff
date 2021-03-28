package validation.error;

public class ExtraFieldValidationError extends  ValidationError{
    private String fieldName;

    public ExtraFieldValidationError(String path, String fieldName){
        super(path);
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return path + ", Extra field <" + fieldName + ">";
    }
}