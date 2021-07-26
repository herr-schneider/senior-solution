package filmjpa;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FilmService {

    private FilmRepo filmRepo;

    private ModelMapper modelMapper;

    public List<FilmDto> listAllFilm() {
        return filmRepo.findAll().stream()
                .map(m -> modelMapper.map(m, FilmDto.class))
                .collect(Collectors.toList());
    }

    public FilmDto createAFilm(CreateFilm createFilm) {
        Film film = new Film(createFilm.getTitle());
        filmRepo.save(film);
        return modelMapper.map(film, FilmDto.class);
    }

    @Transactional
    public FilmDto ratingFilm(long id, RatingFilm command) {
        Film film = filmRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
        film.addRating(command.getRating());
        FilmDto result = modelMapper.map(film, FilmDto.class);
        result.setAverage(film.getRatings().stream()
                .mapToInt(i -> i).average().orElseThrow(() -> new IllegalArgumentException()));
        return result;
//       ratings.stream().mapToInt(i -> i).average().orElseThrow(() -> new IllegalArgumentException());
//       ratings.stream().reduce(0, (subtotal, element) -> subtotal + element);
//       ratings.stream().reduce(0, Integer::sum);
    }
}
