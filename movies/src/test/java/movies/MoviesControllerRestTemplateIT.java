package movies;
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
public class MoviesControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void TestCreateEmployees() {

        template.put("/api/emp", new CreateMovieCommand("Joker", 120));
        template.put("/api/emp", new CreateMovieCommand("Joker", 120));

        System.out.println(template.getForObject("/", List.class));

        //assertEquals("Joker", movieDto.getName());

    }

//    @Test
//    void TestListEmployees() {
//        List<EmployeeDto> result = template.execute("/api/emp/param",
//                HttpMethod.GET,
//                null,
//                null,
//                new ParameterizedTypeReference<List<EmployeeDto>>() {
//                });

//        assertThat(result).
//                extracting(EmployeeDto::getName)
//                .contains("John Doe");
    }
