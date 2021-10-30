package nz.auckland.se754.a6.service;

import nz.auckland.se754.a6.backend.Meeting;
import nz.auckland.se754.a6.backend.User;
import nz.auckland.se754.a6.backend.Group;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {
    private static List<Meeting> meetings = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<Group> groups = new ArrayList<>();


    public MeetingService(){
        User u1 = new User("user1", "1");
        User u2 = new User("user2", "1");
        User u3 = new User("user3", "1");
        User u4 = new User("user4", "1");
        Group g1 = new Group();
        Group g2 = new Group();
        Group g3 = new Group();
        g1.setGroupName("group1");
        g2.setGroupName("group2");
        g3.setGroupName("group3");
        g1.addUsers(u1);
        g1.addUsers(u2);
        g2.addUsers(u3);
        g2.addUsers(u4);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        groups.add(g1);
        groups.add(g2);
        groups.add(g3);

        Meeting m1 = new Meeting();
        m1.setMid("m111");
        m1.setMeetingTitle("title1");
        m1.setMeetingDescription("description1");
        m1.setMeetingTime("13-01-2021 08:00:00", "02-02-2021 10:00:00");
        m1.addUserFromGroup(g1);

        Meeting m2 = new Meeting();
        m2.setMid("m222");
        m2.setMeetingTitle("title2");
        m2.setMeetingDescription("description2");
        m2.setMeetingTime("13-01-3121 10:10:00", "02-09-3121 18:35:00");
        m2.addUserFromGroup(g2);

        meetings.add(m1);
        meetings.add(m2);
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public List<Meeting> getPastMeetings() {
        List<Meeting> pastMeetings = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime lt = LocalDateTime.now();
        String now = lt.format(formatter);
        LocalDateTime nowDateTime = LocalDateTime.parse(now, formatter);
        for (Meeting m: meetings) {
            String end = m.getEndTime();
            LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
            if (nowDateTime.compareTo(endDateTime) > 0) {
                pastMeetings.add(m);
            }
        }
        return pastMeetings;
    }

    public List<Meeting> getUpcomingMeetings() {
        List<Meeting> upcomingMeetings = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime lt = LocalDateTime.now();
        String now = lt.format(formatter);
        LocalDateTime nowDateTime = LocalDateTime.parse(now, formatter);
        for (Meeting m: meetings) {
            String start = m.getStartTime();
            LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
            if (startDateTime.compareTo(nowDateTime) > 0) {
                upcomingMeetings.add(m);
            }
        }
        return upcomingMeetings;
    }

    public List<Group> getGroups() { return groups; }

    public void addMeeting(Meeting m) {
        meetings.add(m);
    }


    public String getDateTimeString (String date, String time) {
        // to "dd-MM-yyyy HH:mm:ss"
        // date = "2021-05-28"
        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];
        // time = "14:00"
        String[] timeParts = time.split(":");
        String hour = timeParts[0];
        String minute = timeParts[1];
        String result = day+"-"+month+"-"+year+" "+hour+":"+minute+":"+"00";
        return result;
    }

    public boolean isValidTime (String start, String end){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(start, format);
        LocalDateTime endDateTime = LocalDateTime.parse(end, format);
        if (endDateTime.compareTo(startDateTime) > 0) {
            return true;
        }else {
            return false;
        }
    }


}
