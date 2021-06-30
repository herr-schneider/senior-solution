package movies;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMovieCommand {

    private String name;
    private long length;

}
