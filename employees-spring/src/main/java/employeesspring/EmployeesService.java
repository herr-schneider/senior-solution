package employeesspring;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeesService {

    private ModelMapper modelMapper;

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
        new Employee(1L, "Jonh Doe"),
        new Employee(2L, "Jane Doe")
    )));

    public List<EmployeeDto> listEmployee(){
        Type targetType = new TypeToken<List<EmployeeDto>>(){}.getType();
        return modelMapper.map(employees, targetType);
    }
}
