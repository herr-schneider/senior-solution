package bikeRender;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {

    public void readFromFile(String fileName) {
        Path file = Path.of(fileName);
        try (BufferedReader bf = Files.newBufferedReader(file)) {
            String line;
            String[] result;
            while ((line = bf.readLine()) != null) {
                result = line.split(";");

            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("File not found", ioe);
        }
    }

    public List<Bike> readFromClasspath() {
        List<Bike> output = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(
                new InputStreamReader(BikeService.class.getResourceAsStream("/bikes.csv")))) {
            String line;
            String[] result;
            String[] dateTime;
            String[] date;
            String[] time;
            LocalDateTime localDateTime;
            while ((line = bf.readLine()) != null) {
                result = line.split(";");
                dateTime = result[2].split(" ");
                date = dateTime[0].split("-");
                time = dateTime[0].split(":");
                localDateTime = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]),
                        Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
                output.add(new Bike(result[0], result[1], localDateTime, Integer.parseInt(result[3])));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("File not found", ioe);
        }
        return output;
    }

    public List<Bike> getBikes() {
        return readFromClasspath();
    }

    public List<String> getUsers() {
        return readFromClasspath().stream()
                .map(Bike::getUser)
                .collect(Collectors.toList());


    }
}
