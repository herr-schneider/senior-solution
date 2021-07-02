package movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MoviesIT {

    @Autowired
    private MoviesController controller;


    @Test
    void getHistoryTest() {
        controller.crateMovie(new CreateMovieCommand("Batman", 120));
        controller.crateMovie(new CreateMovieCommand("Catch if you can", 100));
        List<MovieDto> result = controller.getMovies();
        assertThat(result).hasSize(2)
                .extracting(MovieDto::getName)
                .contains("Batman");
    }
}