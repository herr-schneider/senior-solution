package employeesspringjpa;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    private int minLength;
    private int maxLength;

    @Override
    public void initialize(Name constraintAnnotation) {
        maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null &&
                !value.isBlank() &&
                value.length() > minLength &&
                value.length() < maxLength &&
                Character.isUpperCase(value.charAt(0));
    }
}
