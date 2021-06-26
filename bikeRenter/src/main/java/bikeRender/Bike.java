package bikeRender;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Bike {

    private String azon;
    private String user;
    private LocalDateTime time;
    private double far;

}
