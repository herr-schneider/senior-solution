import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarServiceTest {
CarService car;

    @BeforeEach
    void Setup() {
        car = new CarService();
    }

    @Test
    void listCars() {
        List<Car> result = car.listCars();

        assertThat(result)
                .hasSize(3)
                .extracting(Car::getBrand)
                .contains("Audi", "Ford");
    }

    @Test
    void listBrands() {
        List<String> result = car.getBrands();

        assertThat(result)
                .hasSize(3)
                .contains("Audi", "Ford");
    }
}