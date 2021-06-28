package bikeRender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class bikeControllerTest {

    @Mock
    BikeService bikeService;

    @InjectMocks
    BikeController bikeController;

    @Test
    void testGetUsers(){
        List<String> users = new ArrayList<>(List.of("US346","US3434","US3a34"));
        when(bikeService.getUsers()).thenReturn(users);

        List<String> result = bikeController.getUsers();

        assertThat(result)
                .hasSize(3)
                .contains("US346","US3434","US3a34");

        verify(bikeService, times(1)).getUsers();

    }

    @Test
    void testAllBikes(){
    List<Bike> bikes = new ArrayList<>(List.of(
            new Bike("FH675", "US3434", LocalDateTime.of(2021, 06, 24, 17,12, 50),  0.8),
            new Bike("FH676", "US3a34", LocalDateTime.of(2021, 06, 25, 11,20, 42), 1.2),
            new Bike("FH676", "US3a34", LocalDateTime.of(2021, 06, 25, 12,40, 37), 0.7),
            new Bike("FH676", "US336", LocalDateTime.of(2021, 06, 23, 9,36, 37), 1.9),
            new Bike("FH631", "US346", LocalDateTime.of(2021, 06, 24, 8,53, 37), 1.8)
            ));

    when(bikeService.getBikes()).thenReturn(bikes);

    List<Bike> result = bikeController.getBikes();

    assertThat(result)
                .hasSize(5)
                .extracting(Bike::getUser)
                .contains("US346","US3434","US3a34");

    verify(bikeService, times(1)).getBikes();
    }
}
