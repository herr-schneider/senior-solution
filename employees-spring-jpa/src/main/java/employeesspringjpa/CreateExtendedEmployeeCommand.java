package employeesspringjpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExtendedEmployeeCommand {

    private String name;

    private EmployeeType employeeType = EmployeeType.FULL_TIME;

    private String nickname;

    private ParkingPlace parkingPlace;

    private PhoneNumber phoneNumber;

    private LocalDateTime birthday;

}