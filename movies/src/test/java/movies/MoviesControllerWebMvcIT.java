package movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = MoviesController.class)
public class MoviesControllerWebMvcIT {

    @MockBean
    MoviesService moviesService;

    @Autowired
    MockMvc mockMvc;

    @Test
   void testAllMovies() throws Exception{
        when(moviesService.getMovies())
                .thenReturn(List.of(
                        new MovieDto(1L, "Batman",120, 0),
                        new MovieDto(2L, "News",100, 0),
                        new MovieDto(3L, "Joker",150, 0)
                ));
        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name", equalTo("News")))
                .andDo(print());
    }
}