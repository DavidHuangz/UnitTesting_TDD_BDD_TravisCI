package nz.auckland.se754.a6.controller;

import nz.auckland.se754.a6.backend.GroupHandler;
import nz.auckland.se754.a6.backend.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import nz.auckland.se754.a6.backend.Group;
import nz.auckland.se754.a6.backend.Database;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class CreateGroupController {

        Database db = new Database();
        GroupHandler groupHandler = new GroupHandler(db);
        Group group;
        List<User> UserList = db.intializeUserList();

        @RequestMapping("/group")
        public String showGroupPage(Model model){
            return "group";
        }

        @RequestMapping(value="/group", method = RequestMethod.POST)
        public String createGroup(ModelMap model, @RequestParam String groupName, @RequestParam String userName){

            if (groupName.isEmpty()) {
                model.put("message", "Group can't be created without a group name");
            }
            else if(userName.isEmpty()) {
                model.put("message", "Group can't be created without a user");
            }
            if(!groupName.isEmpty()&& !userName.isEmpty()) {
                group = new Group(db.intializeGroupOwner(), groupName,db.intializeUserList());
                model.put("groupName", groupName);
                model.put("userName", userName);
                model.put("group", group);
                model.put("message", "Your group has been created");
            }

            return "group";
        }


        @RequestMapping("/group-configuration")
        public String showAllUsers(ModelMap model){
            List<String> UserName = new ArrayList<>();
            for(User u:UserList) {
                UserName.add(u.getUsername());
            }
            model.put("AllUserNames", UserName);
            return "GroupConfiguration";
        }

        @RequestMapping(value="/add-user", method = RequestMethod.POST)
        public String addUser(ModelMap model,@RequestParam String userName) {
            if (userName.isEmpty()) {
                model.put("message","You need to enter the username to add a group member");
                return "GroupConfiguration";
            }
            else {
                groupHandler.addUser(new User(userName,"123"),UserList);
                model.put("message", "The user has been added");
            }
            return showAllUsers(model);
        }

        @RequestMapping(value="/remove-user", method = RequestMethod.POST)
        public String removeUser(ModelMap model,@RequestParam String userName) {
            if (userName.isEmpty()) {
                model.put("message","You need to enter the username to add/remove a group member");
                return "GroupConfiguration";
            }
            else {
                groupHandler.removeUser(new User(userName,"123"),UserList);
                model.put("message", "The user has been removed");
            }
            return showAllUsers(model);
        }
}

