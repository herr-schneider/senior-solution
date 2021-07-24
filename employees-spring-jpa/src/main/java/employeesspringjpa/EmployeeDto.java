package employeesspringjpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
