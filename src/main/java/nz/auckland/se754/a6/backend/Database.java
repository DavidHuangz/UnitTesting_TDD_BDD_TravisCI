package nz.auckland.se754.a6.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private List<Group> group;
    private List<Meeting> meeting;
    private List<User> users;
    Map<String,String> currentMeetings = new HashMap<>();

    public Database() {
        group = new ArrayList<>();
        meeting = new ArrayList<>();
        users = new ArrayList<>();
    }


    void saveActivity(List<String> numbers, List<String> suits) { }

    void saveMeeting(Meeting newMeeting){
        meeting.add(newMeeting);
    }

    void saveGroup(User groupOwner, String groupName, List<User> users) { }

    // For UserLoginCreateGroupTest
    void groupSave(Group newGroup) {
        group.add(newGroup);
    }

    public Group getGroup(int groupNum) {
        return group.get(groupNum);
    }

    public Meeting getMeeting(int meetingNumber) {
        return meeting.get(meetingNumber);
    }

    public User intializeGroupOwner() {
        User groupOwner = new User("userName","123");
        return groupOwner;
    }

    public List<User> intializeUserList() {
        User user2 = new User("userName2", "123");
        User user3 = new User("userName3", "123");
        users.add(user2);
        users.add(user3);
        return users;
    }

    public List<Meeting> initializeMeetings() {
        Meeting m1 = new Meeting("001", "Meeting1");
        Meeting m2 = new Meeting("002", "Meeting2");
        Meeting m3 = new Meeting("003", "Meeting3");
        Meeting m4 = new Meeting("004", "Meeting4");
        meeting.add(m1);
        meeting.add(m2);
        meeting.add(m3);
        meeting.add(m4);
        return meeting;
    }
}
