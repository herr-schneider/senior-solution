package apeh;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApehController {

    public ApehController(ApehService apehService) {
        this.apehService = apehService;
    }

    private final ApehService apehService;

    @GetMapping("/types")
    public List<CaseType> listType() {
        return apehService.listTypes();
    }

    @PostMapping("/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    public CaseTypeDto addMovie(@Valid @RequestBody CreateAppointmentCommand command) {
        return apehService.addAppointment(command);}
}
