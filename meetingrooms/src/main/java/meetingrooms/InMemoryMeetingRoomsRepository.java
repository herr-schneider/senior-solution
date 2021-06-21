package meetingrooms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryMeetingRoomsRepository implements MeetingroomsRepository {
    List<Meetingroom> meetingrooms = new ArrayList<>();


    public void deleteAll() {
        meetingrooms.clear();
    }

    @Override
    public void create(String name, int width, int length) {
        meetingrooms.add(new Meetingroom(name, width, length));
    }

    @Override
    public List<Meetingroom> listABC() {
        return meetingrooms.stream()
                .sorted(Comparator.comparing(Meetingroom::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meetingroom> reservedListABC() {
        return meetingrooms.stream()
                .sorted(Comparator.comparing(Meetingroom::getName).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> evenList() {
        return IntStream.range(0, meetingrooms.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(meetingrooms::get)
                .map(Meetingroom::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meetingroom> areaList() {
        return meetingrooms.stream()
                .sorted(Comparator.comparing(Meetingroom::getArea))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> search(String find) {
        return meetingrooms.stream()
                .map(Meetingroom::getName)
                .filter(s -> s.equals(find))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meetingroom> partialSearch(String find) {
        return meetingrooms.stream()
                .filter(s -> s.getName().contains(find))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meetingroom> areaSearch(int area) {
        return meetingrooms.stream()
                .filter(meetingroom -> meetingroom.getArea() == area)
                .collect(Collectors.toList());
    }
}
