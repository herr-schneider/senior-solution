package employeesspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void TestCreateEmployees() {
        EmployeeDto employeeDto =
                template.postForObject("/api/emp", new CreateEmployeeCommand("John Doe"), EmployeeDto.class);

        assertEquals("John Doe", employeeDto.getName());
        //assertThat(employeeDto.getName()).isEqualTo("John Doe");
    }

    @Test
    void TestListEmployees() {
        List<EmployeeDto> result = template.exchange("/api/emp/param",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDto>>() {
                }).getBody();

        System.out.println(result);
//        assertThat(result).
//                extracting(EmployeeDto::getName)
//                .contains("John Doe");
    }
}
