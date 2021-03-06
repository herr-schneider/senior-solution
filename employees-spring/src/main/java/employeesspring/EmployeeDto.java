package employeesspring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;

    public EmployeeDto(String name) {
        this.name = name;
    }
}
