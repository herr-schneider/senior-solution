package employeesspringjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private EmployeesDao employeesDao;

    @BeforeEach
    void  init() {
       employeesDao = new EmployeesDao(Persistence.createEntityManagerFactory("pu"));
    }


    @Test
    void setNicknames() {
    Employee e = new Employee("John Doe");
    e.setNicknames(Set.of("okoska", "néger"));
    employeesDao.saveEmployee(e);
        System.out.println(employeesDao.listEmployee());
        Employee ae = employeesDao.findEmployeeByNameWithNicknames("John Doe");
        System.out.println(ae);
    }
}