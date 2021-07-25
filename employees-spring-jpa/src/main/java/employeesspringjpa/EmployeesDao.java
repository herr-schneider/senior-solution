package employeesspringjpa;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


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

//    public Employee findEmployeeByIDWithNicknames(long id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Employee employee = entityManager.createQuery("select e from Employee e join fetch e.nicknames where e.id = :id",
//                Employee.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        entityManager.close();
//        return employee;
//    }

    public Employee saveEmployee(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
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

    public void deleteByID(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        //return employee;
    }

    public List<Employee> listEmployeeParam(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employee> filtered = entityManager.createQuery("select em from Employee em where em.name like :name", Employee.class)
                .setParameter("name", name)
                .getResultList();
        entityManager.close();
        return filtered;
    }

//    public Employee findEmployeeByNameWithNicknames(String name) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Employee employee = entityManager.createQuery("select e from Employee e join fetch e.nicknames where e.name like :name",
//                Employee.class)
//                .setParameter("name", name)
//                .getSingleResult();
//        entityManager.close();
//        return employee;
//    }

    public Employee findEmployeeByNameWithPhoneNumbers(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employee employee = entityManager.createQuery("select e from Employee e join fetch e.phoneNumbers where e.name like :name",
                Employee.class)
                .setParameter("name", name)
                .getSingleResult();
        entityManager.close();
        return employee;
    }

    public Employee addPhoneNumber(long emp_id, PhoneNumber phoneNumber){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //Employee employee = entityManager.find(Employee.class, emp_id);
        Employee employee = entityManager.getReference(Employee.class, emp_id);
        phoneNumber.setEmployee(employee);
        entityManager.persist(phoneNumber);
        entityManager.getTransaction().commit();
        entityManager.close();
        return employee;
    }
}
