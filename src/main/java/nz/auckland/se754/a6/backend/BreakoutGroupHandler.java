package nz.auckland.se754.a6.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BreakoutGroupHandler {
    private List<User> main_group;
    private List<BreakoutGroup> breakout_groups;

    public BreakoutGroupHandler(List<User> users) {
        main_group = new ArrayList<>(List.copyOf(users));
        breakout_groups = new ArrayList<>();
    }

    public List<User> getMainGroup() {
        return main_group;
    }

    public List<BreakoutGroup> getBreakoutGroups() {
        return breakout_groups;
    }

    public List<BreakoutGroup> makeNumberOfBreakoutGroups(int number_of_groups, boolean should_add_users) {
        double max_users_per_group = Math.ceil(main_group.size()/(double)number_of_groups);
        List<BreakoutGroup> groups = new ArrayList<>();

        List<User> randomised_users = new ArrayList<>(List.copyOf(main_group));
        Collections.shuffle(randomised_users);

        List<User> group_users = new ArrayList<>();
        for (int i = 0; i < randomised_users.size(); i++) {
            group_users.add(randomised_users.get(i));

            if (group_users.size() >= max_users_per_group || i == randomised_users.size()-1) {
                BreakoutGroup new_group;
                if (should_add_users) {
                    new_group = new BreakoutGroup(group_users);
                } else {
                    new_group = new BreakoutGroup();
                }
                groups.add(new_group);
                group_users = new ArrayList<>();
            }
        }

        breakout_groups.addAll(groups);

        if (should_add_users) {
            main_group.clear();
        }

        return groups;
    }

    public List<BreakoutGroup> makeBreakoutGroupsForNumberOfUsers(int num_users_per_group, boolean should_add_users) {
        List<User> randomised_users = new ArrayList<>(List.copyOf(main_group));
        Collections.shuffle(randomised_users);

        List<BreakoutGroup> groups = new ArrayList<>();
        List<User> group_users = new ArrayList<>();
        for (int i = 0; i < randomised_users.size(); i++) {
            group_users.add(randomised_users.get(i));

            if (group_users.size() >= num_users_per_group || i == randomised_users.size()-1) {
                BreakoutGroup new_group;
                if (should_add_users) {
                    new_group = new BreakoutGroup(group_users, num_users_per_group);
                } else {
                    new_group = new BreakoutGroup(num_users_per_group);
                }
                groups.add(new_group);
                group_users = new ArrayList<>();
            }
        }

        breakout_groups.addAll(groups);

        if (should_add_users) {
            main_group.clear();
        }

        return groups;
    }

    public void disbandBreakoutGroup(int index_to_disband) {
        if (index_to_disband >= breakout_groups.size()) {
            throw new UnsupportedOperationException("No corresponding breakout group");
        }

        BreakoutGroup group = breakout_groups.get(index_to_disband);
        List<User> group_users = group.getUsers();

        main_group.addAll(group_users);
        breakout_groups.remove(index_to_disband);
    }

    public void addUserToBreakoutGroup(User user, int group_index) {
        breakout_groups.get(group_index).addUser(user);
    }

    public void addUserToMainGroup(User user) {
        main_group.add(user);
    }

    public void removeUserFromBreakoutGroup(User user, int group_index) {
        breakout_groups.get(group_index).removeUser(user);
    }

    public List<BreakoutGroup> getAvailableBreakoutGroups() {
        List<BreakoutGroup> available_groups = new ArrayList<>();

        for (BreakoutGroup group : breakout_groups) {
            if (group.hasAvailability()) {
                available_groups.add(group);
            }
        }

        return available_groups;
    }

    public BreakoutGroup getGroupForUser(User u) {
        for (BreakoutGroup group : breakout_groups) {
            if (group.getUsers().contains(u)) {
                return group;
            }
        }

        return null;
    }
}
