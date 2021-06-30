package movies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoviesControllerTestIT {

    @Autowired
    MoviesController controller;

    @Test
    void getMovies() {

    }

    @Test
    void findMovieByID() {
    }

    @Test
    void deleteMovie() {
    }
}