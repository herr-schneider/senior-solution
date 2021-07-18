package apeh;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class IntervaldValidator implements ConstraintValidator<Interval, CreateAppointmentCommand> {

    @Override
    public boolean isValid(CreateAppointmentCommand createAppointmentCommand, ConstraintValidatorContext constraintValidatorContext) {
        return createAppointmentCommand.getStartDate().isAfter(LocalDateTime.now()) &&
                createAppointmentCommand.getEndDate().isAfter(createAppointmentCommand.getStartDate());
    }
}
