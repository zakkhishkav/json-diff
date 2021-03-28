package validation.error;

public abstract class ValidationError {
    protected String path;

    public ValidationError(String path){
        this.path = path;
    }
}