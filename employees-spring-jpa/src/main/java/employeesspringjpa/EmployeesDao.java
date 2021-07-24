package employeesspringjpa;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeesDao {

    private EntityManagerFactory entityManagerFactory;

    private ModelMapper modelMapper;

    public EmployeesDao(EntityManagerFactory entityManagerFactory, ModelMapper modelMapper) {
        this.entityManagerFactory = entityManagerFactory;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> listEmployee() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<EmployeeDto> result = entityManager.createQuery("select em from Employee em", Employee.class)
                .getResultStream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
        entityManager.close();
        return result;
    }

    public EmployeeDto findEmployeeByID(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.close();
        return modelMapper.map(employee, EmployeeDto.class);
    }


    public EmployeeDto createEmployee(CreateEmployeeCommand createEmployeeCommand) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = new Employee(createEmployeeCommand.getName());
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        employee.setName(command.getName());
        entityManager.getTransaction().commit();
        entityManager.close();
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteByID(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Employee> listEmployeeParam(Optional<String> prefix) {
        Type targetType = new TypeToken<List<EmployeeDto>>() {
        }.getType();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> filtered = entityManager.createQuery("select em from Employee em", Employee.class)
                .getResultList();
        entityManager.close();
        return filtered;
    }
}
