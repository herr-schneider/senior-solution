package movies;

import lombok.Data;

@Data
public class MovieDto {
    private long id;
    private String name;
    private long length;
    private double average;
}
