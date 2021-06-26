package employeesspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emp")
public class EmployeesController {

    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployeeDto() {
        return employeesService.listEmployeeDto();
    }

    @GetMapping("/notDto")
    public List<Employee> listEmployee() {
        return employeesService.listEmployee();
    }

    @GetMapping("/param")
    public List<EmployeeDto> listEmployeeParam(@RequestParam Optional<String> prefix) {
        return employeesService.listEmployeeParam(prefix);
    }

    @GetMapping("/param/{id}")
    public EmployeeDto findEmployeeByID(@PathVariable("id") long id) {
        return employeesService.findEmployeeByID(id);
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeCommand createEmployeeCommand){
        return employeesService.createEmployee(createEmployeeCommand);
    }

    @PutMapping("/param/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/param/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }
}
