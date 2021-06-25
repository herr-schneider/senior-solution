package bikeRender;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bike {

    private String azon;
    private String user;
    private LocalDateTime time;
    private double far;

    public Bike(String azon, String user, LocalDateTime time, double far) {
        this.azon = azon;
        this.user = user;
        this.time = time;
        this.far = far;
    }

}
