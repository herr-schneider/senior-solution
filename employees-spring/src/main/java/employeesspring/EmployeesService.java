package employeesspring;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private ModelMapper modelMapper;
    private AtomicLong idgen = new AtomicLong();

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

//    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
//            new Employee(idgen.incrementAndGet(), "John Doe"),
//            new Employee(idgen.incrementAndGet(), "Jane Doe")
//    )));

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
           new Employee(idgen.incrementAndGet(), "John Doe"),
            new Employee(idgen.incrementAndGet(), "Jane Doe")
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
                .orElseThrow(() -> new IllegalArgumentException("There is no " + id)),
                EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand createEmployeeCommand){
        Employee employee = new Employee(idgen.incrementAndGet(), createEmployeeCommand.getName());
        employees.add(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("Error"));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("Error"));
        employees.remove(employee);
    }
}
