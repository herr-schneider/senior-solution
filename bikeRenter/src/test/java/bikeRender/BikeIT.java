package bikeRender;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BikeIT {

    @Autowired
    private BikeController bc;


    @Test
    void getHistoryTest() {
        List<Bike> result = bc.getBikes();
        assertThat(result).hasSize(5)
                .extracting(Bike::getAzon)
                .contains("FH675");
    }


    @Test
    void testAllBiker() {
        List<Bike> result = bc.getBikes();

        assertThat(result)
                .hasSize(5)
                .extracting(Bike::getAzon)
                .contains("FH675", "FH676");
    }

    @Test
    void testAllUser() {
        List<String> result = bc.getUsers();

        assertThat(result)
                .hasSize(5)
                .contains("US3434", "US346");
    }
}
