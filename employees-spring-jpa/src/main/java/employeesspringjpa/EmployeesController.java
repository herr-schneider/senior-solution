package employeesspringjpa;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.List;
import java.util.Map;
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

//    @GetMapping("/param/{id}")
//    public ResponseEntity findEmployeeByID(@PathVariable("id") long id) {
//        try {
//            return ResponseEntity.ok(employeesService.findEmployeeByID(id));
//        }catch (IllegalArgumentException iae) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeCommand createEmployeeCommand) {
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
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

//    @DeleteMapping("/param/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public EmployeeDto deleteEmployee(@PathVariable("id") long id) {
//        return employeesService.deleteEmployee(id);
//    }

    @DeleteMapping("/param/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
       employeesService.deleteEmployee(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("/api/emp/param"))
                .withTitle("Not found!")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @GetMapping("/test")
    public String dontDoThat(@RequestParam Map<String, String> myParam) { //ResponseBody párja
        // return myParam.values().toString();
        // return myParam.keySet().toString();
        return myParam.toString();
    }

//    data  http
//    Create POST
//    Read GET
//    Update PUT
//    Delete DELETE
}