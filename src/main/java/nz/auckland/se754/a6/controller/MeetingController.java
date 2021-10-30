package nz.auckland.se754.a6.controller;

import nz.auckland.se754.a6.backend.Group;
import nz.auckland.se754.a6.backend.Meeting;
import nz.auckland.se754.a6.backend.User;
import nz.auckland.se754.a6.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MeetingController {
    @Autowired
    MeetingService service;

    List<String> groupNames;
    List<Group> groups;


    @RequestMapping (value="/createMeeting", method = RequestMethod.GET)
    public String showCreateMeetingPage(ModelMap model){

        groups = service.getGroups();
        groupNames = new ArrayList<>();
        for (Group g: groups){
            groupNames.add(g.getGroupName());
        }

        model.put("groupNames", groupNames);
        return "createMeeting";
    }

    @RequestMapping(value="/createMeeting", method = RequestMethod.POST)
    public String createMeeting(ModelMap model,
                                @RequestParam String meetingID,
                                @RequestParam String meetingTitle,
                                @RequestParam String meetingDescription,
                                @RequestParam String startDate,
                                @RequestParam String startTime,
                                @RequestParam String endDate,
                                @RequestParam String endTime,
                                @RequestParam String chooseGroup) {
        model.put("meetingID", meetingID );
        model.put("meetingTitle", meetingTitle );
        model.put("meetingDescription", meetingDescription );
        model.put("startDate", startDate);
        model.put("startTime", startTime);
        model.put("endDate", endDate);
        model.put("endTime", endTime);
        model.put("selectedGroupName", chooseGroup);

        groups = service.getGroups();
        groupNames = new ArrayList<>();
        for (Group g: groups){
            groupNames.add(g.getGroupName());
        }

        model.put("groupNames", groupNames);

        if (startDate.equals("")
                || startTime.equals("")
                || endDate.equals("")
                || endTime.equals("")) {
            model.put("message", "You need to choose the meeting time!");
            return "createMeeting";
        }

        if (chooseGroup.equals("")) {
            model.put("message", "No groups available");
            return "createMeeting";
        }

        if ( meetingID.equals("") ) {
            model.put("message", "You need to enter the meeting ID!");
            return "createMeeting";
        }

        String startDateTime = service.getDateTimeString(startDate,startTime);
        String endDateTime = service.getDateTimeString(endDate, endTime);
        boolean isValidTime = service.isValidTime(startDateTime, endDateTime);
        if ( !isValidTime ) {
            model.put("message", "Invalid Meeting Time!");
            return "createMeeting";
        }

        Meeting meeting = new Meeting();
        meeting.setMid(meetingID);
        meeting.setMeetingTitle(meetingTitle);
        meeting.setMeetingDescription(meetingDescription);
        meeting.setMeetingTime(startDateTime, endDateTime);

        for(Group g: groups){
            if (g.getGroupName().equals(chooseGroup)){
                meeting.addUserFromGroup(g);
                break;
            }
        }

        service.addMeeting(meeting);
        model.put("message", "The meeting has been created");


        return "createMeeting";
    }



}
