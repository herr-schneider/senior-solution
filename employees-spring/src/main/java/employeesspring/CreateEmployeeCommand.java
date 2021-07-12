package employeesspring;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
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
