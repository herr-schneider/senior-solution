package employeesspringjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import javax.persistence.Persistence;
import java.util.Optional;
import java.util.Set;

class EmployeeTest {

    private EmployeesService service;

    @BeforeEach
    void  init() {
       service = new EmployeesService(new ModelMapper());
    }


    @Test
    void setNicknames() {
    Employee e = new Employee("John Doe");
    e.setNicknames(Set.of("okoska", "néger"));
    service.saveEmployee(e);
       System.out.println(service.listEmployee());
        EmployeeExtendedDto ae = service.findEmployeeByNameWithNicknames(Optional.of("John Doe"));
        System.out.println(ae);
    }
}