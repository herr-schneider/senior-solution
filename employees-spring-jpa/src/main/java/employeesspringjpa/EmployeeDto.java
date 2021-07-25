package employeesspringjpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;
//    private Set<String> nicknames;

    public EmployeeDto(String name) {
        this.name = name;
    }

//    public EmployeeDto(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
