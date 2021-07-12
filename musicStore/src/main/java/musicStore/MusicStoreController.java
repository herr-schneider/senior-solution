package musicStore;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @ResponseStatus(HttpStatus.CREATED)
    public InstrumentDTO addInstrument(@Valid @RequestBody CreateInstrumentCommand newInstrument) {
        return musicStoreService.addInstrument(newInstrument);
    }

//    @PutMapping("/{id}")
//    public InstrumentDTO updateInstrument(@PathVariable("id") long id, @Valid @RequestBody CreateInstrumentCommand newInstrument){
//        return musicStoreService.updateInstrument(id, newInstrument);
//    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public InstrumentDTO updatePrice(@PathVariable("id") long id, @Valid @RequestBody UpdatePriceCommand command) {
        return musicStoreService.updatePrice(id, command);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Problem> handleValidException(MethodArgumentNotValidException mnve) {
        List<Violation> violations = mnve.getBindingResult().getFieldErrors().stream()
                .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        Problem problem = Problem.builder()
                .withType(URI.create("instruments/not-valid"))
                .withTitle("Validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(mnve.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
    //    data  http
//    Create POST többször idempotens
//    Read GET
//    Update PUT cssak egyszer fut le
//    Delete DELETE


}
