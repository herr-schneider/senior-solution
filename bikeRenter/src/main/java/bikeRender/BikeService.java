package bikeRender;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@Service
public class BikeService {

    private List<Bike> bikes = new ArrayList<>();

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

    public void readFromClasspath() {
        System.out.println("File reading!");
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
               bikes.add(proccessLine(line));
//                result = line.split(";");
//                dateTime = result[2].split(" ");
//                date = dateTime[0].split("-");
//                time = dateTime[1].split(":");
//                localDateTime = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]),
//                        Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
//                bikes.add(new Bike(result[0], result[1], localDateTime, Double.parseDouble(result[3]))); //valueOf helyett
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("File not found", ioe);
        }
    }

    public Bike proccessLine(String line) {
        String[] temp = line.split(";");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime actTime = LocalDateTime.parse(temp[2], formatter);
        return new Bike(temp[0], temp[1], actTime, Double.parseDouble(temp[3]));
    }

    public List<Bike> getBikes() {
        if(bikes.isEmpty()) readFromClasspath();
        return bikes;
    }

    public List<String> getUsers() {
        if(bikes.isEmpty()) readFromClasspath();
        return bikes.stream()
                .map(Bike::getUser)
                .distinct()
                .collect(Collectors.toList());
    }

    public String greeting() {
        return "Hello";
    }
}
