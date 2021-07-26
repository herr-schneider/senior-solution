package filmjpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "movies")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection
    private List<Integer> ratings;

    public Film(String title) {
        this.title = title;
    }

    public Film(String title, List<Integer> ratings) {
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
