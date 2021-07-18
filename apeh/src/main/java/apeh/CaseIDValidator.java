package apeh;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class CaseIDValidator implements ConstraintValidator<CaseID, String> {

    @Autowired
    private ApehService apehService;

//    public CaseIDValidator(ApehService apehService) {
//        this.apehService = apehService;
//    }

    @Override
    public void initialize(CaseID constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String caseID, ConstraintValidatorContext constraintValidatorContext) {
        return apehService.validCaseID(caseID);
    }
}
