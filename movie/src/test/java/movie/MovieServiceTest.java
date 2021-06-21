package movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    MovieService movieService;

    @BeforeEach
    void init(){
        movieService = new MovieService();
        movieService.save(new Movie("van dame", 120, LocalDate.of(2020,05, 03)));
        movieService.save(new Movie("Dragula", 100, LocalDate.of(2019,05, 03)));
        movieService.save(new Movie("True Lies", 160, LocalDate.of(2020,04, 03)));

    }
    @Test
    void theNewestFilmTest() {
        Movie movie = movieService.theNewestFilm();

        assertEquals(movie.getReleaseDate(), LocalDate.of(2020,05, 03));
    }

    @Test
    void theNewestFilmsTest() {
        List<Movie> movies = movieService.theNewestFilms();

        movies.stream()
                .map(Movie::getName)
                .forEach(System.out::println);
    }

    @Test
    void searchFilmByDateTest() {
        Movie movie = movieService.searchFilmByDate(LocalDate.of(2020,05, 03));

        assertEquals(movie.getName(), "van dame");
      }

    @Test
    void searchFilmTest() {
        Movie movie = movieService.searchFilm("Dragula");

        assertEquals(movie.getName(), "Dragula");
    }

    @Test
    void partSearchFilmTest() {
        Movie movie = movieService.searchFilm("ragu");

        assertEquals(movie.getName(), "Dragula");
    }
}