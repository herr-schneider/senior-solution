package movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoviesControllerRestTest {

    @Autowired
    TestRestTemplate template;

    @Test
    void getMovies() {
        MovieDto result = template.postForObject("/api/movies", new CreateMovieCommand("Titanic", 100), MovieDto.class);
        assertEquals("Titanic", result.getName());
    }

    @Test
    void findMovieByID() {
    }

    @Test
    void deleteMovie() {
    }
}