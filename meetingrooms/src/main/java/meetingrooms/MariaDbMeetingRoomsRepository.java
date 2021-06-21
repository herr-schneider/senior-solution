package meetingrooms;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariaDbMeetingRoomsRepository implements MeetingroomsRepository {

    private JdbcTemplate jdbcTemplate;

    public MariaDbMeetingRoomsRepository() {
        try {
            MariaDbDataSource dataSource;
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3308/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();

            flyway.migrate();

            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (SQLException e) {
            throw new IllegalStateException("Can not create datasource", e);
        }

    }

    public void deleteAll() {
        jdbcTemplate.update("delete from  meetingroom");
    }

    @Override
    public void create(String name, int length, int width) {
        jdbcTemplate.update("insert into meetingroom(room_name, length, width) values(?, ?, ?)", name, length, width);
    }

    @Override
    public List<Meetingroom> listABC() {
        return jdbcTemplate.query("select id, room_name, width, length from meetingroom order by room_name",
                (rs, i) -> new Meetingroom(rs.getLong("id"), rs.getString("room_name"), rs.getInt("width"), rs.getInt("length")));

    }

    @Override
    public List<Meetingroom> reservedListABC() {
        return jdbcTemplate.query("s select id, room_name, width, length from meetingroom order by room_name desc",
                (rs, i) -> new Meetingroom(rs.getLong("id"), rs.getString("room_name"), rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public List<String> evenList() {
        return jdbcTemplate.query("SELECT `room_name` FROM (SELECT `room_name`, row_number() over (ORDER BY `room_name`) as `rn` FROM `meetingrooms`) as `w_rownum` WHERE w_rownum.rn % 2 = 0 ORDER BY `name`",
                (rs, i) -> rs.getString("room_name"));

    }

    @Override
    public List<Meetingroom> areaList() {
        return jdbcTemplate.query("select id, room_name, width, length from meetingroom order BY (`length` * width);",
                (rs, i) -> new Meetingroom(rs.getLong("id"), rs.getString("room_name"), rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public List<String> search(String find) {
        return null;
    }

    @Override
    public List<Meetingroom> partialSearch(String find) {
        return jdbcTemplate.query("select id, room_name, width, length from meetingroom where room_name = ?;",
                new Object[]{find}, (rs, i) -> new Meetingroom(rs.getLong("id"), rs.getString("room_name"), rs.getInt("width"), rs.getInt("length")));
    }

    @Override
    public List<Meetingroom> areaSearch(int area) {
        return jdbcTemplate.query("select id, room_name, width, length from meetingroom where (`length` * width) = ?;",
                new Object[]{area}, (rs, i) -> new Meetingroom(rs.getLong("id"), rs.getString("room_name"), rs.getInt("width"), rs.getInt("length")));
    }

    public List<Meetingroom> findAll() {
        List<Meetingroom> meetingrooms = jdbcTemplate.query("select id, room_name, width, length from meetingroom ORDER BY room_name;",
                (rs, i) -> new Meetingroom(rs.getLong("id"), rs.getString("room_name"), rs.getInt("width"), rs.getInt("length")));
    for (Meetingroom meetingroom : meetingrooms){
        jdbcTemplate.query("select id, room_id, meeting_name, start, end from meetingroom WHERE room_id = ?;",
                (rs, i) -> new Meeting(rs.getLong("id"), rs.getString("meeting_name"), rs.getTimestamp("start").toLocalDateTime(),
                        rs.getTimestamp("end").toLocalDateTime(), rs.getLong("room_id")),
                meetingroom.getId())
        .stream()
        .forEach(meetingroom::addMeeting); //a -> meetingroom.addMeeting(a);
    }

        return meetingrooms;
    }
}
