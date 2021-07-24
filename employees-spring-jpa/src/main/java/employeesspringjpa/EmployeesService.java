package employeesspringjpa;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Persistence;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeesService {


    @Autowired
    private EmployeesDao employeesDao; // = new EmployeesDao(Persistence.createEntityManagerFactory("pu"));

    @Autowired
    private ModelMapper modelMapper;

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<EmployeeDto> listEmployee() {
        return employeesDao.listEmployee();
    }

    public List<EmployeeDto> listEmployeeParam(Optional<String> prefix) {
        Type targetType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employeesDao.listEmployeeParam(prefix), targetType);
    }

    public EmployeeDto findEmployeeByID(long id) {
        return employeesDao.findEmployeeByID(id);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand createEmployeeCommand){
        return employeesDao.createEmployee(createEmployeeCommand);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
       return employeesDao.updateEmployee(id, command);
    }

    public void deleteEmployee(long id) {
        employeesDao.deleteByID(id);
    }
}
