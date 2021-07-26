package filmjpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

//@AllArgsConstructor
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {

    @Id
    private long id;

    private String title;

    @ElementCollection
    private List<Integer> ratings = new ArrayList<>();

    private double average;

    public FilmDto(long id, String title, List<Integer> ratings) {
        this.id = id;
        this.title = title;
        this.ratings = ratings;
    }


    public void addRating(int rate) {
        if (ratings == null) {
            ratings = new ArrayList<>();
        }
        ratings.add(rate);
    }
}
