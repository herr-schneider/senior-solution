package apeh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentCommand {

    @TaxID
    private long taxNumber;

    @CaseID
    private String caseID;

    private LocalDateTime startDate;
    private LocalDateTime endDate;


}
