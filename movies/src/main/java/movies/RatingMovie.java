package movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingMovie {
    private int rate;

//    public RatingMovie(int rate) {
//        this.rate = rate;
//    }
//
//    public int getRate() {
//        return rate;
//    }
}
