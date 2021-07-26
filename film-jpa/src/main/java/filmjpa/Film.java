package filmjpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Film {

    @Id
    private long id;

    private String title;

    @ElementCollection
    private List<Integer> ratings;

    public Film(String title) {
        this.title = title;
    }

    public void addRating(int rate){
        if (ratings == null) {ratings = new ArrayList<>();}
        ratings.add(rate);
    }
}
