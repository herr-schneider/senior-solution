package movies;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MoviesService {
    List<Movie> movies = new ArrayList<>();

    private ModelMapper modelMapper;

    private AtomicLong id; // = new AtomicLong();

    public MoviesService(ModelMapper modelMapper, AtomicLong atomicLong) {
        this.id = atomicLong;
        this.modelMapper = modelMapper;
    }

//    public double ratingMovie(long id, int rate) {
//        movies.stream()
//                .filter(m -> m.getId() == id)
//                .forEach(movie -> movie.addRate(rate));
//
//        return movies.stream()
//                .filter(m -> m.getId() == id)
//                .map(movie -> movie.getAverage())
//                .findFirst()
//                .get();
//    }

    public MovieDto ratingMovie(long id, RatingMovie rate) {
        Movie movie = movies.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no " + id));
        movie.addRate(rate.getRate());
        return modelMapper.map(movie, MovieDto.class);
    }

    public MovieDto findMovieByID(long id) {
        return modelMapper.map(movies.stream()
                        .filter(m -> m.getId() == id)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("There is no " + id)),
                MovieDto.class);
    }

    public void deleteEmployee(long id) {
        Movie movie = movies.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Error"));
        movies.remove(movie);
    }

    public List<MovieDto> getMovies() {
        Type targetType = new TypeToken<List<MovieDto>>() {
        }.getType();
        return modelMapper.map(new ArrayList<>(movies), targetType);
    }

    public MovieDto crateMovie(CreateMovieCommand command) {
        Movie movie = new Movie(id.incrementAndGet(), command.getName(), command.getLength());
        movies.add(movie);
        return modelMapper.map(movie, MovieDto.class);
    }
}
