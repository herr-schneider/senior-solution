package apeh;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TaxIdValidator.class)
public @interface TaxID {

    String message() default "Invalid tax number!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
