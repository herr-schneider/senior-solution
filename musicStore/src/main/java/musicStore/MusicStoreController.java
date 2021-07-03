package musicStore;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instruments")
public class MusicStoreController {

    private MusicStoreService musicStoreService;

    public MusicStoreController(MusicStoreService musicStoreService) {
        this.musicStoreService = musicStoreService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InstrumentDTO> getInstruments(@RequestParam Optional<String> brand, @RequestParam Optional<Integer> price) {
        return musicStoreService.getInstruments(brand, price);
    }

    @PostMapping
    public InstrumentDTO addInstrument(@Valid @RequestBody CreateInstrumentCommand newInstrument) {
        return musicStoreService.addInstrument(newInstrument);
    }

//    @PutMapping("/{id}")
//    public InstrumentDTO updateInstrument(@PathVariable("id") long id, @Valid @RequestBody CreateInstrumentCommand newInstrument){
//        return musicStoreService.updateInstrument(id, newInstrument);
//    }

    @PutMapping("/{id}")
    public InstrumentDTO updatePrice(@PathVariable("id") long id, @Valid @RequestBody UpdatePriceCommand command) {
        return musicStoreService.updatePrice(id, command);
    }

    @GetMapping("/{id}")
    public InstrumentDTO getInstrument(@PathVariable("id") long id) {
        return musicStoreService.getInstrument(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void deleteMovie() {
        musicStoreService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void deleteMovieByID(@PathVariable("id") long id) {
        musicStoreService.deleteMovieByID(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae){
        Problem problem = Problem.builder()
                .withType(URI.create("instruments/not-found"))
                .withTitle("Not found!")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    //    data  http
//    Create POST többször idempotens
//    Read GET
//    Update PUT cssak egyszer fut le
//    Delete DELETE

}
