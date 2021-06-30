package movies;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Movie {

    private long id;
    private String name;
    private long length;
    private List<Integer> imdb = new ArrayList<>();
    private double average;


    public Movie(long id, String name, long length) {
        this.id = id;
        this.name = name;
        this.length = length;
    }

    public void addRate(int rate){
        imdb.add(rate);

        average = imdb.stream().collect(Collectors.summarizingInt(Integer::intValue)).getAverage();
    }

//    public double getAverage(){
//        int i = imdb.stream()
//                .reduce(0, Integer::sum);
//        long j = imdb.stream()
//                .count();
//        return (double) (i/j);
//    }
}
