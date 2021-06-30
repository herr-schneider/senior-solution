package movies;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class CreateMovieCommand {

    @NotBlank(message = "Nem vagy ilyen hulye!")
    private String name;

    @Min(5)
    @Max(200)
    private long length;

}
