package nz.auckland.se754.a6.backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Meeting {

    private Database _db;
    private String _mid;
    private String _title;
    private String _description;
    private String _startTime;
    private String _endTime;
    private List<User> _users;
    private BreakoutGroupHandler _groupHandler;


    public Meeting() {
        this._users = new ArrayList<User>();
        _groupHandler = new BreakoutGroupHandler(_users);
    }

    public Meeting (Database db) {
        _db = db;
        this._users = new ArrayList<User>();
        _groupHandler = new BreakoutGroupHandler(_users);
    }

    public Meeting (String meetingID, String meetingTitle) {
        _mid = meetingID;
        _title = meetingTitle;
    }

    public void save() {
        _db.saveMeeting(this);
    }

    public String getMid() {return _mid;}

    public String getMeetingTitle () {
        return _title;
    }

    public String getMeetingDescription () { return _description; }

    public List<User> getUsers() { return _users; }

    public String getStartTime () { return _startTime; }

    public String getEndTime () { return _endTime; }

    public void addUserToMeeting (User user){
        _users.add(user);
        _groupHandler.addUserToMainGroup(user);
    }

    public void setUsers (List<User> newUserList){
        this._users = newUserList;
        this._groupHandler = new BreakoutGroupHandler(newUserList);
    }

    public void setMid (String mid) {_mid = mid;}

    public void setMeetingTitle (String title) { this._title = title;}

    public void setMeetingDescription (String description) {this._description = description;}

    public void addUserFromGroup (Group group){
        for (User u: group.getUsers()){
            _users.add(u);
        }
    }

    public List<String> getAllUserNameForAMeeting () {
        List<String> names = new ArrayList<>();
        List<User> users = getUsers();
        for (User u: users){
            names.add(u.getName());
        }
        return names;
    }

    public List<String> getAllUsernames () {
        List<String> usernames = new ArrayList<>();
        List<User> users = getUsers();
        for (User u: users){
            usernames.add(u.getUsername());
        }
        return usernames;
    }

    public List<String> getAllUserIdForAMeeting () {
        List<String> idList = new ArrayList<>();
        List<User> users = getUsers();
        for (User u: users){
            idList.add(u.getUid());
        }
        return idList;
    }

    public boolean setMeetingTime (String start, String end) {
        // "13-01-2021 17:09:42";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(start, format);
        LocalDateTime endDateTime = LocalDateTime.parse(end, format);
        if (endDateTime.compareTo(startDateTime) > 0) {
            this._startTime = start;
            this._endTime = end;
            return true;
        }else {
            return false;
        }
    }

    public Boolean removeUserFromMeeting (User user){
        List<User> newUsersList = new ArrayList<User>();
        Boolean contain = false;
        for (User u: _users){
            if (u.getUid() == user.getUid()) {
                contain = true;
            }else{
                newUsersList.add(u);
            }
        }
        if (contain){
            setUsers(newUsersList);
            return true;
        }else{
            return false;
        }
    }

    public BreakoutGroupHandler getGroupHandler() {
        return _groupHandler;
    }
}
