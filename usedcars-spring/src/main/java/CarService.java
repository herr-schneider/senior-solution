import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    List<Car> cars = new ArrayList<>(List.of(new Car("Ford", "Mustang", 20, CarState.FINE),
            new Car("Audi", "A6", 10, CarState.NOTBAD),
            new Car("Mercedes", "Vito", 5, CarState.FINE)));

    public  List<Car> listCars() {
        return cars;
    }

    public List<String> getBrands() {
    return cars.stream().map(Car::getBrand)
            .distinct()
    .collect(Collectors.toList());}

}
