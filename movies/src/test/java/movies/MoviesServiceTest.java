package movies;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MoviesServiceTest {

    MoviesService service = new MoviesService(new ModelMapper(), new AtomicLong()); //3rd library-t hasznal, ezert nem unit test

    @Test
    void ratingMovie() {
        service.crateMovie(new CreateMovieCommand("batman",120));
        MovieDto testDto = service.ratingMovie(1, new RatingMovie(4));
        assertThat(testDto.getAverage())
                .isEqualTo(4);
        testDto = service.ratingMovie(1, new RatingMovie(5));
        assertThat(testDto.getAverage())
                .isEqualTo(4.5);
        testDto = service.ratingMovie(1, new RatingMovie(4));
        assertThat(testDto.getAverage()).isCloseTo(4.33, Percentage.withPercentage(0.3));
        testDto = service.ratingMovie(1, new RatingMovie(5));
        assertThat(testDto.getAverage())
                .isEqualTo(4.5);
    }

    @Test
    void findMovieByID() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void getMovies() {
    }

    @Test
    void crateMovie() {

    }
}