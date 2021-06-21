package movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    List<Movie> movies = new ArrayList<>();

    public void save(Movie movie){
        movies.add(movie);
    }

    public Movie searchFilmByDate(LocalDate release){
    return movies.stream()
            .filter(m -> m.getReleaseDate().isEqual(release))
            .findFirst()
            .get();
//            .findAny()
//            .get();
    }

    public Movie theNewestFilm(){
        return movies.stream()
                .max(Comparator.comparing(Movie::getReleaseDate))
                .get();
    }

    public List<Movie> theNewestFilms(){
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getReleaseDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Movie> searchFilms(String find){
        return movies.stream()
                .filter(m -> m.getName().contains(find))
                .distinct()
                .collect(Collectors.toList());
    }

    public Movie searchFilm(String find){
        return movies.stream()
                .filter(m -> m.getName().contains(find))
                .findFirst()
                .get();
    }
}
