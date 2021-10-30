package nz.auckland.se754.a6.backend;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class User {

    private String _name;
    private String _uid;
    private List<Meeting> _meetings;
    private boolean login;
    private final String userName;
    private String password;

    public User(String username, String pass) {
        login = false;
        userName = username;
        password = pass;
        this._meetings = new ArrayList<>();
    }

    public void setLogin() {
        login = true;
    }

    public boolean getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return userName;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public void setLogOut() {
        login = false;
    }

    public void setName (String name){ this._name=name; }

    public void setUid (String id){ this._uid=id; }

    public String getUid() { return this._uid; }

    public String getName() { return this._name; }

    public List<Meeting> getAllMeetingsForAUser() { return this._meetings; }

    public void addMeeting (Meeting meeting){
        this._meetings.add(meeting);
    }

    public List<Meeting> getUpcomingMeetings() {
        List<Meeting> upcomingMeetings = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime lt = LocalDateTime.now();
        String now = lt.format(formatter);
        LocalDateTime nowDateTime = LocalDateTime.parse(now, formatter);
        for (Meeting m: getAllMeetingsForAUser()) {
            String start = m.getStartTime();
            LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
            if (startDateTime.compareTo(nowDateTime) > 0) {
                upcomingMeetings.add(m);
            }
        }
        return upcomingMeetings;
    }

    public List<Meeting> getPastMeetings() {
        List<Meeting> pastMeetings = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime lt = LocalDateTime.now();
        String now = lt.format(formatter);
        LocalDateTime nowDateTime = LocalDateTime.parse(now, formatter);
        for (Meeting m: getAllMeetingsForAUser()) {
            String end = m.getEndTime();
            LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
            if (nowDateTime.compareTo(endDateTime) > 0) {
                pastMeetings.add(m);
            }
        }
        return pastMeetings;
    }

    public List<Meeting> getMeetingsForSpecificDate(String dateString) {
        // "13-01-2021"
        List<Meeting> meetings = new ArrayList<>();
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, f2);
        for (Meeting m: getAllMeetingsForAUser()) {
            String start = m.getStartTime();
            LocalDateTime startDateTime = LocalDateTime.parse(start, f1);
            boolean condition = date.getYear()==startDateTime.getYear()
                    && date.getMonthValue() == startDateTime.getMonthValue()
                    && date.getDayOfMonth() == startDateTime.getDayOfMonth();
            if ( condition ){
                meetings.add(m);
            }
        }
        return meetings;
    }

    public List<Meeting> getCurrentMeetings() {
        List<Meeting> currentMeetings = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime lt = LocalDateTime.now();
        String now = lt.format(formatter);
        for (Meeting m: getAllMeetingsForAUser()){
            LocalDateTime nowDateTime = LocalDateTime.parse(now, formatter);
            String start = m.getStartTime();
            String end = m.getEndTime();
            LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
            if (nowDateTime.isAfter(startDateTime) && nowDateTime.isBefore(endDateTime)) {
                currentMeetings.add(m);
            }
        }
        return currentMeetings;
    }

    public Meeting joinAMeeting(Meeting meeting) {
        Meeting ongoingMeeting = null;
        for(Meeting m:getCurrentMeetings()) {
            if(meeting == m) {
                ongoingMeeting = m;
            }
        }

        if(ongoingMeeting == null) {
            throw new UnsupportedOperationException ("The meeting you are looking for is not active");
        }

        ongoingMeeting.addUserToMeeting(this);
        return ongoingMeeting;
    }

}
