import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Car {

    private String manufact;
    private String type;
    private long age;
    private CarState state;
}
