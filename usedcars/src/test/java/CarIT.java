import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarIT {

    @Autowired
    CarController carController;

    @Test
    void listCars() {
        List<Car> result = carController.listCars();

        assertThat(result)
                .hasSize(3)
                .extracting(Car::getBrand)
                .contains("Audi", "Mercedes");
    }

    @Test
    void listBrands() {
        List<String> result = carController.listBrands();

        assertThat(result)
                .hasSize(3)
                .contains("Audi", "Ford");
    }
}