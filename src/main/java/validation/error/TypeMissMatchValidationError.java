package validation.error;

public class TypeMissMatchValidationError extends  ValidationError{

    private String type1;
    private String type2;

    public TypeMissMatchValidationError(String path , String type1, String type2){
        super(path);
        this.type1 = type1;
        this.type2 = type2;
    }

    @Override
    public String toString() {
        return path + ", Different types " + type1 + " != " + type2;
    }
}