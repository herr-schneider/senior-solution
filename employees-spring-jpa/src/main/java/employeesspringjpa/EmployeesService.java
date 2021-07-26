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

    private EmployeesDao employeesDao = new EmployeesDao(Persistence.createEntityManagerFactory("pu"));

    private ModelMapper modelMapper;

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> listEmployee() {
        Type targetType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
//        List<EmployeeDto> result = employeesDao.listEmployee().stream()
//                .map(e -> modelMapper.map(e, EmployeeDto.class))
//                .collect(Collectors.toList());
//        return result;
        return modelMapper.map(employeesDao.listEmployee(), targetType);
    }

    public List<EmployeeDto> listEmployeeParam(Optional<String> prefix) {
        String name = "%";
        if (prefix.isPresent()){name= "%"+prefix.get()+"%";}
        Type targetType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        return modelMapper.map(employeesDao.listEmployeeParam(name), targetType);
    }
//    public EmployeeExtendedDto  findEmployeeByNameWithNicknames(Optional<String> prefix) {
//        String name = "%";
//        if (prefix.isPresent()){name= "%"+prefix.get()+"%";}
//        Type targetType = new TypeToken<List<EmployeeExtendedDto>>() {
//        }.getType();
//        return modelMapper.map(employeesDao.findEmployeeByNameWithNicknames(name), EmployeeExtendedDto.class);
//    }

    public EmployeeDto findEmployeeByID(long id) {
        return modelMapper.map(employeesDao.findEmployeeByID(id), EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand createEmployeeCommand){
        return modelMapper.map(employeesDao.createEmployee(createEmployeeCommand), EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        return modelMapper.map(employeesDao.updateEmployee(id, command), EmployeeDto.class);
    }

//    public EmployeeDto deleteEmployee(long id) {
//        return modelMapper.map(employeesDao.deleteByID(id), EmployeeDto.class);
//    }
    public void deleteEmployee(long id) {
        employeesDao.deleteByID(id);
    }

    public EmployeeExtendedDto saveEmployee(Employee e) {
        return modelMapper.map(employeesDao.saveEmployee(e), EmployeeExtendedDto.class);
    }
}
