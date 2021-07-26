package employeesspringjpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

//    private Set<String> nicknames;

    public EmployeeDto(String name) {
        this.name = name;
    }

    public EmployeeDto(long id, String name) {
        this.name = name;
        this.id = id;
    }

//    public EmployeeDto(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
