package employeesspringjpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateEmployeeCommand {

    @Name(minLength = 3, maxLength = 40)
    private String name;

    public CreateEmployeeCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
