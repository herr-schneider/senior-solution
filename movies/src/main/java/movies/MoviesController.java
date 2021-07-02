package movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> getMovies(){
        return moviesService.getMovies();
    }

    @GetMapping("/{id}")
    public MovieDto findMovieByID(@PathVariable("id") long id) {
        return moviesService.findMovieByID(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae){
        Problem problem = Problem.builder()
                .withType(URI.create("/api/movies"))
                .withTitle("Not found!")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @PostMapping("/{id}/rating")
    public MovieDto ratingMovie(@PathVariable("id") long id, @RequestBody RatingMovie rate) { //uj osztalyt letrehozni
         return moviesService.ratingMovie(id, rate);
    }


//    @PostMapping("{id}/rating")
//    public double ratingMovie(@PathVariable("id") long id, @RequestParam RatingMovie rate) { //uj osztalyt letrehozni
//        try {
//            return moviesService.ratingMovie(id, rate);
//        }catch (IllegalArgumentException iae) {
//           throw new IllegalArgumentException();
//        }
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void deleteMovie(@PathVariable("id") long id) {
        moviesService.deleteEmployee(id);
    }

    @PutMapping
    public MovieDto crateMovie(@RequestBody CreateMovieCommand command){
        return moviesService.crateMovie(command);
    }


//    data  http
//    Create POST többször idempotens
//    Read GET
//    Update PUT cssak egyszer fut le
//    Delete DELETE
}
