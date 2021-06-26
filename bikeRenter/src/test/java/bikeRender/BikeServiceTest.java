package bikeRender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BikeServiceTest {
    BikeService bike;

    @BeforeEach
    void Setup() {
        bike = new BikeService();
    }

    @Test
    void getAllBike() {
        List<Bike> result = bike.getBikes();

        assertThat(result)
                .hasSize(5)
                .extracting(Bike::getFar)
                .contains(0.8, 1.2);
    }

    @Test
    void listBrands() {
        List<String> result = bike.getUsers();

        assertThat(result)
                .hasSize(5)
                .contains("US3434", "US3a34", "US3334", "US336", "US346");
    }
}