import java.time.LocalDate;

public class KmState {
    LocalDate date;
    long km;

    public KmState(LocalDate date, long km) {
        this.date = date;
        this.km = km;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getKm() {
        return km;
    }

    public void setKm(long km) {
        this.km = km;
    }
}
