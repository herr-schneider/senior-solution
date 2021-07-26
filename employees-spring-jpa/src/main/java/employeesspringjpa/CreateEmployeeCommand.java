package employeesspringjpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    @Name(minLength = 3, maxLength = 40)
    private String name;

    private LocalDateTime birthday;

    public CreateEmployeeCommand(String name) {
        this.name = name;
    }

//    public String getName() {
//        return name;
//    }
}
