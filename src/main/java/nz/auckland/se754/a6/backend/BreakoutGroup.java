package nz.auckland.se754.a6.backend;

import java.util.ArrayList;
import java.util.List;

public class BreakoutGroup {
    private List<User> users;
    private int max_users;

    public BreakoutGroup() {
        this(80);
    }

    public BreakoutGroup(int max) {
        max_users = max;
        users = new ArrayList<>();
    }

    public BreakoutGroup(List<User> u) {
        this(u, 80);
    }

    public BreakoutGroup(List<User> u, int max) {
        max_users = max;
        users = new ArrayList<>(List.copyOf(u));
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User new_user) {
        if (users.size() == max_users) {
            throw new UnsupportedOperationException("Breakout group is full");
        }
        users.add(new_user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public boolean hasAvailability() {
        return users.size() < max_users;
    }
}
