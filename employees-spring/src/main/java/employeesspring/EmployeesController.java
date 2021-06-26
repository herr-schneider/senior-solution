package employeesspring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity findEmployeeByID(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(employeesService.findEmployeeByID(id));
        }catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeCommand createEmployeeCommand){
        return employeesService.createEmployee(createEmployeeCommand);
    }

//    @PutMapping("/param/{id}")
//    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
//        return employeesService.updateEmployee(id, command);
//    }

    @PutMapping("/param/{id}")
    public ResponseEntity updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
        try {
            return ResponseEntity.ok(employeesService.updateEmployee(id, command));
        }catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/param/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }
}