package employeesspring;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private ModelMapper modelMapper;

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(1L, "John Doe"),
            new Employee(2L, "Jane Doe")
    )));

    public List<Employee> listEmployee() {
        return employees;
    }

    public List<EmployeeDto> listEmployeeDto() {
        Type targetType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employees, targetType);
    }

    public List<EmployeeDto> listEmployeeParam(Optional<String> prefix) {
        Type targetType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        List<Employee> filtered = employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetType);
    }

    public EmployeeDto findEmployeeByID(long id) {
        return modelMapper.map(employees.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no" + id)),
                EmployeeDto.class);
    }
}
