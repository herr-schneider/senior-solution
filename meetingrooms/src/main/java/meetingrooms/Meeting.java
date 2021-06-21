package meetingrooms;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Meeting {
    private long id;
    private String name;
    private long room_id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Meeting(){}

    public Meeting(long id, String name, LocalDateTime startTime, LocalDateTime endTime, long room_id) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room_id = room_id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
