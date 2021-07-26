package filmjpa;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FilmService {

    private FilmRepo filmRepo;

    public List<Film> listAllFilm() {
        return filmRepo.findAll();
    }

    public Film createAFilm(CreateFilm createFilm){
        Film film = new Film(createFilm.getTitle());
        filmRepo.save(film);
        return film;
    }

    @Transactional
    public Film ratingFilm(long id, RatingFilm command){
       Film film = filmRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
       film.addRating(command.getRating());
       return film;
    }
}
