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
        List<MovieDto> result = controller.getMovies();
        assertThat(result).hasSize(5)
                .extracting(MovieDto::getName)
                .contains("Batman");
    }
}