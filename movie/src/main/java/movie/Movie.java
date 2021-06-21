package movie;

import java.time.LocalDate;

public class Movie {
    private String name;
    private int length;
    private LocalDate releaseDate;

    public Movie(String name, int length, LocalDate releaseDate) {
        this.name = name;
        this.length = length;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
