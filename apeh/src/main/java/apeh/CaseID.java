package apeh;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CaseIDValidator.class)
public @interface CaseID {

    String message() default "Invalid date!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}