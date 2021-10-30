package nz.auckland.se754.a6.controller;

import nz.auckland.se754.a6.backend.BreakoutGroup;
import nz.auckland.se754.a6.backend.User;
import nz.auckland.se754.a6.service.BreakoutGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BreakoutGroupController {
    @Autowired
    BreakoutGroupsService service;

    @RequestMapping(value = "/breakout-group", method = RequestMethod.GET)
    public String configPage(ModelMap model) {
        return "breakout-group-config";
    }

    @RequestMapping(value = "/breakout-group", method = RequestMethod.POST)
    public String configPagePost(ModelMap model, String format) {
        model.put("format", format);

        // TODO: Integration of meeting
        int num_users_in_meeting = 10;
        model.put("meeting_users", num_users_in_meeting);

        return "breakout-group-config";
    }

    @RequestMapping(value = "/breakout-group/create", method = RequestMethod.POST)
    public String createGroups(ModelMap model, String format, String users_in_meeting, String number_users, String number_groups, String allocation) {
        boolean isValid = true;

        if (users_in_meeting == null || users_in_meeting.equals("")) {
            model.put("error", "No users in meeting");
            isValid = false;
        }

        if (allocation == null || (!(allocation.equals("random")) && !(allocation.equals("choice")))) {
            model.put("error", "Must choose allocation format");
            isValid = false;
        }

        if (format.equals("users")) {
            if (number_users == null || number_users.equals("")) {
                model.put("error", "Must specify number of users");
                isValid = false;
            }
        } else if (format.equals("groups")) {
            if (number_groups == null || number_groups.equals("")) {
                model.put("error", "Must specify number of groups");
                isValid = false;
            }
        } else {
            model.put("error", "Invalid format");
            isValid = false;
        }

        if (isValid) {
            int num_param;
            if (format.equals("users")) {
                num_param = Integer.parseInt(number_users);
            } else {
                num_param = Integer.parseInt(number_groups);
            }
            boolean allocate_students = false;
            if (allocation.equals("random")) {
                allocate_students = true;
            }

            service.setRandomUserList(Integer.parseInt(users_in_meeting));
            service.makeGroups(format, num_param, allocate_students);
            List<BreakoutGroup> allGroups = service.getAllGroups();
            model.put("group_list", formatGroupList(allGroups));

            return "breakout-group-join";
        } else {
            model.put("format", format);

            // TODO: Integration of meeting
            int num_users_in_meeting = 10;
            model.put("meeting_users", num_users_in_meeting);

            return "breakout-group-config";
        }
    }

    @RequestMapping(value = "/breakout-group/join", method = RequestMethod.POST)
    public String createGroups(ModelMap model, String username, String group_number) {

        if (group_number == null || group_number == "" ) {
            List<BreakoutGroup> allGroups = service.getAllGroups();
            model.put("group_list", formatGroupList(allGroups));
        } else {
            BreakoutGroup group = service.getAllGroups().get(Integer.valueOf(group_number));

            try {
                if (username == null || username == "") {
                    service.addAnonymousUserToGroup(group);
                }

                model.put("users", formatGroupUsers(group));
                model.put("group_number", group_number);

            } catch (UnsupportedOperationException e) {
                model.put("error", e.getMessage());
                List<BreakoutGroup> allGroups = service.getAllGroups();
                model.put("group_list", formatGroupList(allGroups));
            }
        }

        return "breakout-group-join";
    }

    @RequestMapping(value = "/breakout-group/disband", method = RequestMethod.POST)
    public String disbandGroups(ModelMap model, String group_id) {
        service.disbandGroup(Integer.valueOf(group_id));

        List<BreakoutGroup> allGroups = service.getAllGroups();
        model.put("group_list", formatGroupList(allGroups));

        return "breakout-group-join";
    }

    private List<String> formatGroupUsers(BreakoutGroup group) {
        List<String> user_list = new ArrayList<>();
        for (User u : group.getUsers()) {
            user_list.add(u.getUsername());
        }
        return user_list;
    }

    private List<String[]> formatGroupList(List<BreakoutGroup> allGroups) {
        List<String[]> strings = new ArrayList<>();
        for (BreakoutGroup group : allGroups) {
            String name = "Group " + (allGroups.indexOf(group)+1);

            List<User> group_users = group.getUsers();
            String users_string = "";
            for (User u : group_users) {
                users_string += ", "+u.getUsername();
            }
            if (!(users_string.equals(""))) {
                users_string = users_string.substring(2);
            }

            String index = String.valueOf(allGroups.indexOf(group));

            String[] group_data = new String[]{name, users_string, index};
            strings.add(group_data);
        }
        return strings;
    }
}