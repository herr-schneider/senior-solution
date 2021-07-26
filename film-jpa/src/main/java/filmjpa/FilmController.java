package filmjpa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class FilmController {

    private FilmService filmService;

    @GetMapping
    public List<Film> listAllFilm() {
        return filmService.listAllFilm();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film createEmployee(@RequestBody CreateFilm command) {
        return filmService.createAFilm(command);
    }

    @PutMapping("/{id}/rating")
    public ResponseEntity ratingFilm(@PathVariable("id") long id, @RequestBody RatingFilm command) {
        try {
            return ResponseEntity.ok(filmService.ratingFilm(id, command));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }
}
