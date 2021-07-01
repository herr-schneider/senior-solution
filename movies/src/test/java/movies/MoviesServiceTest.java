package movies;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class MoviesServiceTest {

    MoviesService service = new MoviesService(new ModelMapper()); //3rd library-t hasznal, ezert nem unit test

    @Test
    void ratingMovie() {
        service.crateMovie(new CreateMovieCommand("batman",120));
        System.out.println(service.ratingMovie(1, new RatingMovie(4)));
        System.out.println(service.ratingMovie(1, new RatingMovie(5)));
        System.out.println(service.ratingMovie(1, new RatingMovie(4)));
        System.out.println(service.ratingMovie(1, new RatingMovie(5)));


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