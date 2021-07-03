package movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoviesControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    void TestCreateMovies() {

        template.put("/api/movies", new CreateMovieCommand("Joker", 120));
        template.put("/api/movies", new CreateMovieCommand("Joker", 120));
        template.put("/api/movies", new CreateMovieCommand("Batman", 100));

        List<MovieDto> result = template.exchange("/api/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MovieDto>>() {
                }).getBody();

        assertThat(result)
                .hasSize(3)
                .extracting(MovieDto::getName)
                .contains("Batman");

//                template.put("/api/instruments/1", new UpdatePriceCommand(1000));
//
//        InstrumentDTO result = template.getForObject("/api/instruments/1",InstrumentDTO.class);
    }

    @Test
    void TestCreateMovie() {
     //   MovieDto movieDto =
                template.put("/api/movies",
                        new CreateMovieCommand("Valami amerika",120), MovieDto.class);
        HttpEntity<CreateMovieCommand> requestEntity = new HttpEntity<>(new CreateMovieCommand("Valami amerika",120));

        MovieDto movieDto = template.exchange("/api/movies",
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<MovieDto>() {
                }).getBody();

        assertEquals("Valami amerika", movieDto.getName());
        //assertThat(employeeDto.getName()).isEqualTo("John Doe");
    }


}
