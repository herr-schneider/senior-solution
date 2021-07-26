package filmjpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {

    @Id
    private long id;

    private String title;

    @ElementCollection
    private List<Integer> ratings;

    public FilmDto(String title) {
        this.title = title;
    }

    public FilmDto(String title, List<Integer> ratings) {
        this.title = title;
        this.ratings = ratings;
    }

    public void addRating(int rate){
        if (ratings == null) {ratings = new ArrayList<>();}
        ratings.add(rate);
    }
}
