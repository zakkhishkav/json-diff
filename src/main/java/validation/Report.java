package validation;

import validation.error.ValidationError;

import java.util.LinkedList;
import java.util.List;

public class Report {
    private List<ValidationError> errors = new LinkedList<>();

    public Report() {
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public boolean isValid() {
        return errors.size() == 0;
    }

    public void addError(ValidationError error) {
        errors.add(error);
    }

    public void printErrorLog() {
        errors.forEach(System.out::println);
    }
}

