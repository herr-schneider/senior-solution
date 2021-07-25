package employeesspringjpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeExtendedDto {

    private Long id;
    private String name;
    private Set<String> nicknames;
    private EmployeeType employeeType;

    public EmployeeExtendedDto(String name) {
        this.name = name;
    }

    public EmployeeExtendedDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
