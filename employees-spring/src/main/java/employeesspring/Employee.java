package employeesspring;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


//@RequiredArgsConstructor
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class Employee {
    private Long id;
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
