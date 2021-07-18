package apeh;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaxIdValidator implements ConstraintValidator<TaxID, String> {
    @Override
    public void initialize(TaxID constraintAnnotation) {
        ;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            int taxNum = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
