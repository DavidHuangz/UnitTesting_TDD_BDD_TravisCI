package nz.auckland.se754.a6.backend;

import java.util.ArrayList;
import java.util.List;

public class GroupHandler {

    private Database _db;

    public GroupHandler(Database db) {
        _db = db;
    }

    public Group createGroup(User newGroupOwner, String newGroupName, List<User> users) {
        List<User> _allUsers;
        _allUsers = new ArrayList<>();
        Group newGroup;
        String groupName = "";
        User groupOwner;
        if(!newGroupName.isEmpty()) {
            groupName = newGroupName;
        }
        else {
            throw new UnsupportedOperationException ("Your group has not been created because of missing group name input");
        }

        if(newGroupOwner != null) {
            groupOwner = newGroupOwner;
        }
        else {
            throw new UnsupportedOperationException ("Your group has not been created because it needs to have at least one member");
        }

        for (int i = 0; i<users.size(); i++) {
                _allUsers.add(users.get(i));
        }

        newGroup = new Group(groupOwner, groupName,_allUsers);
        return newGroup;
    }

    public void addUser(User user, List<User> all_users) {
        all_users.add(user);
    }

    public void removeUser(User user, List<User> all_users) {
        for (int i = 0; i<all_users.size(); i++) {
            User users = all_users.get(i);
            if(user.getUsername().equals(users.getUsername())) {
                all_users.remove(i);
                break;
            }
        }

    }

    public void save(Group group) {
        _db.saveGroup(group.getGroupOwner(),group.getGroupName(), group.getUsers());
    }
}
