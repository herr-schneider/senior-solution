package movies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoviesControllerTest {

    @Mock
    MoviesService service;

    @InjectMocks
    MoviesController controller;

    //MoviesController moviesController = new MoviesController(new MoviesService(new ModelMapper()));

    @Test
    void createAndRatingMovie() {
        MovieDto input = new MovieDto(1, "Batman", 120, 3.5);
        when(service.crateMovie(any())).thenReturn(input);
        when(service.ratingMovie(1, new RatingMovie(4))).thenReturn(input);
        controller.crateMovie(new CreateMovieCommand("batman", 120));
        MovieDto result = controller.ratingMovie(1, new RatingMovie(4));

        System.out.println(result);
        assertThat(result)
                .extracting(MovieDto::getAverage)
                .isEqualTo(3.5);

        assertThat(result)
                .extracting(MovieDto::getName)
                .isEqualTo("Batman");

        verify(service, times(1)).crateMovie(any());
        verify(service, times(1)).ratingMovie(1L, new RatingMovie(4));
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