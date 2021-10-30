package nz.auckland.se754.a6.service;

import nz.auckland.se754.a6.backend.BreakoutGroup;
import nz.auckland.se754.a6.backend.BreakoutGroupHandler;
import nz.auckland.se754.a6.backend.Meeting;
import nz.auckland.se754.a6.backend.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreakoutGroupsService {
    private static Meeting meeting = new Meeting();

    /**
     * Utility method for injecting random users into the meeting
     * @return
     */
    public void setRandomUserList(int num_users) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < num_users; i++) {
            users.add(new User("user-"+i, "pass"));
        }
        meeting.setUsers(users);
    }

    public List<BreakoutGroup> makeGroups(String format, int num_param, boolean allocate_students) {
        if (format.equals("users")) {
            return meeting.getGroupHandler().makeBreakoutGroupsForNumberOfUsers(num_param, allocate_students);
        } else {
            return meeting.getGroupHandler().makeNumberOfBreakoutGroups(num_param, allocate_students);
        }
    }

    public BreakoutGroup getGroupForUser(User me) {
        return meeting.getGroupHandler().getGroupForUser(me);
    }

    public List<BreakoutGroup> getAllGroups() {
        return meeting.getGroupHandler().getBreakoutGroups();
    }

    public void addAnonymousUserToGroup(BreakoutGroup group) {
        User anon_user = new User("Anonymous", "");
        group.addUser(anon_user);
    }

    public void disbandGroup(int index) {
        meeting.getGroupHandler().disbandBreakoutGroup(index);
    }
}
