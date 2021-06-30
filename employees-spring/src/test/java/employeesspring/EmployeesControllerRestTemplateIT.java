package employeesspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void TestListEmployees(){
        EmployeeDto employeeDto =
                template.postForObject("/api/emp", new CreateEmployeeCommand("John Doe"), EmployeeDto.class);

        assertEquals("John Doe", employeeDto.getName());
        //assertThat(employeeDto.getName()).isEqualTo("John Doe");
    }
}
