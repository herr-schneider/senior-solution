package movies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MoviesControllerTest {

    @Mock
    MoviesService service;

    @InjectMocks
    MoviesController controller;

    MoviesController moviesController = new MoviesController(new MoviesService(new ModelMapper()));

    @Test
    void ratingMovie(){
        moviesController.crateMovie(new CreateMovieCommand("batman", 120));
        moviesController.ratingMovie(1, new RatingMovie(5));
    }
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