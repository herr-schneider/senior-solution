package movies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MoviesControllerTest {

    @Mock
    MoviesService service;

    @InjectMocks
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