package nz.auckland.se754.a6.backend;
import java.util.ArrayList;
import java.util.List;

public class Group {

    private List<User> _allUsers;
    private List<Meeting> _allMeetings;
    private String _groupName;
    private User _groupOwner;

    public Group() {
        _allUsers = new ArrayList<>();
        _allMeetings = new ArrayList<>();
    }

    public Group(User groupOwner, String groupName, List<User> users) {
        _groupName = groupName;
        _groupOwner = groupOwner;
        _allUsers = users;
        _allMeetings = new ArrayList<>();
    }

    public void addUsers (User user) { _allUsers.add(user); }

    public void setGroupName (String name) {_groupName = name; }

    public void setUsers(List<User> users) {
        this._allUsers = users;
    }

    public void addMeeting (Meeting meeting) {
        this._allMeetings.add(meeting);
        for(User u: _allUsers){
            u.addMeeting(meeting);
        }
    }

    public List<Meeting> getMeetings() {
        return this._allMeetings;
    }

    public List<User> getUsers() {
        return _allUsers;
    }

    public String getGroupName() {
        return _groupName;
    }

    public User getGroupOwner() {return _groupOwner;}
}


