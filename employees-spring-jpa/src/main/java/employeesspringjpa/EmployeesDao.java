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

    public EmployeesDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Employee> listEmployee() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> result = entityManager.createQuery("select em from Employee em", Employee.class)
             .getResultList();
        entityManager.close();
        return result;
    }

    public Employee findEmployeeByID(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.close();
        return employee;
    }


    public Employee createEmployee(CreateEmployeeCommand createEmployeeCommand) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = new Employee(createEmployeeCommand.getName());
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        return employee;
    }

    public Employee updateEmployee(long id, UpdateEmployeeCommand command) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        employee.setName(command.getName());
        entityManager.getTransaction().commit();
        entityManager.close();
        return employee;
    }

    public Employee deleteByID(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        return employee;
    }

    public List<Employee> listEmployeeParam(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> filtered = entityManager.createQuery("select em from Employee em where em.name like :name", Employee.class)
                .setParameter("name", name)
                .getResultList();
        entityManager.close();
        return filtered;
    }
}
