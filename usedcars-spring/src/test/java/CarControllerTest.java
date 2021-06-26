import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @Test
    void listCars() {
        List<Car> cars = new ArrayList<>(List.of(new Car("Ford", "Mustang", 20, CarState.FINE),
                new Car("Audi", "A6", 10, CarState.NOTBAD),
                new Car("Mercedes", "Vito", 5, CarState.FINE)));

        when(carController.listCars()).thenReturn(cars);

        List<Car> result = carController.listCars();

        assertThat(result)
                .hasSize(3)
                .extracting(Car::getBrand)
                .contains("Audi", "Ford");

        verify(carService, times(1)).listCars();
    }

    @Test
    void listBrands() {
        List<String> cars = new ArrayList<>(List.of("Audi", "Ford", "Mercedes"));

        when(carService.getBrands()).thenReturn(cars);

        List<String> result = carController.listBrands();

        assertThat(result)
                .hasSize(3)
                .contains("Audi", "Ford");

        verify(carService, times(1)).listCars();
    }

}
