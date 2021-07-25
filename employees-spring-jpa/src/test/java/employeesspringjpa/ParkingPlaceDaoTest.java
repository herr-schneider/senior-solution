package employeesspringjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {

    private ParkingPlaceDao parkingPlaceDao;
    private EmployeesDao employeesDao;

    @BeforeEach
     void init(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        parkingPlaceDao =  new ParkingPlaceDao(entityManagerFactory);
        employeesDao = new EmployeesDao(entityManagerFactory);

    }

    @Test
    void saveParking() {
        parkingPlaceDao.saveParking(new ParkingPlace(100));
        ParkingPlace result = parkingPlaceDao.findParkingPlace(100);
        assertEquals(100, result.getNumber());
    }

    @Test
    void saveEmployeeWithParking() {
        ParkingPlace parkingPlace = new ParkingPlace(100);
        parkingPlaceDao.saveParking(parkingPlace);

        Employee employee = new Employee("Johny");
        employee.setParkingPlace(parkingPlace);
        employeesDao.saveEmployee(employee);

        Employee ae = employeesDao.findEmployeeByID(employee.getId());
        assertEquals(100, ae.getParkingPlace().getNumber());
    }

    @Test
    void saveEmployeeWithPhoneNumber() {
        PhoneNumber homeNumber = new PhoneNumber("home","1234");
        PhoneNumber workNumber = new PhoneNumber("work","4321");

        Employee employee = new Employee("Johny");
        employee.setPhoneNumbers(List.of(homeNumber, workNumber));
        employeesDao.saveEmployee(employee);

        Employee ae = employeesDao.findEmployeeByID(employee.getId());
    }

    @Test
    void saveEmployeeWithPhoneNumberWithAdd() {
        PhoneNumber homeNumber = new PhoneNumber("home","1234");
        PhoneNumber workNumber = new PhoneNumber("work","4321");

        Employee employee = new Employee("Johny");
        employee.addPhoneNumber(homeNumber);
        employee.addPhoneNumber(workNumber);
        employeesDao.saveEmployee(employee);

        Employee ae = employeesDao.findEmployeeByNameWithPhoneNumbers("Johny");
        assertEquals("1234", ae.getPhoneNumbers().get(0).getPhoneNumber());
    }

    @Test
    void testAddPhoneNumber() {
        Employee employee = new Employee("Johny");
        employeesDao.saveEmployee(employee);
        PhoneNumber homeNumber = new PhoneNumber("home","1234");
        PhoneNumber workNumber = new PhoneNumber("work","4321");
        employeesDao.addPhoneNumber(employee.getId(),homeNumber);
        employeesDao.addPhoneNumber(employee.getId(),workNumber);

        Employee ae = employeesDao.findEmployeeByNameWithPhoneNumbers("Johny");
        assertEquals("4321", ae.getPhoneNumbers().get(0).getPhoneNumber());;
    }

    @Test
    void testRemoveEmployeeWithPhoneNumbers(){
        Employee employee = new Employee("Johny");
        employeesDao.saveEmployee(employee);
        PhoneNumber homeNumber = new PhoneNumber("home","1234");
        PhoneNumber workNumber = new PhoneNumber("work","4321");
        employeesDao.addPhoneNumber(employee.getId(),homeNumber);
        employeesDao.addPhoneNumber(employee.getId(),workNumber);

        Employee ae = employeesDao.findEmployeeByNameWithPhoneNumbers("Johny");
        employeesDao.deleteByID(ae.getId());
    }
}