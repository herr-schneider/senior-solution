package cinema;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.Nullable;
import java.net.URI;

public class MovieNotFoundException extends AbstractThrowableProblem {

    public MovieNotFoundException() {
    }

    public MovieNotFoundException(long id) {
       super(URI.create("cinema/not-found"),
               "Not-found",
               Status.NOT_FOUND,
               String.format("Movies id %dnot found ", id));
    }

    public MovieNotFoundException(@Nullable URI type) {
        super(type);
    }
}
