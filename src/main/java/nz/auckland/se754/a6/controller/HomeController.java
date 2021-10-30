package nz.auckland.se754.a6.controller;


import nz.auckland.se754.a6.backend.Meeting;
import nz.auckland.se754.a6.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;



import java.util.List;


@Controller
public class HomeController {


    @Autowired
    MeetingService meetingService;

    List<Meeting> allMeetings;
    List<Meeting> pastMeetings;
    List<Meeting> upcomingMeetings;


    @RequestMapping (value="/home", method = RequestMethod.GET)
    public String showHomePage(ModelMap model){
        allMeetings = meetingService.getMeetings();
        pastMeetings = meetingService.getPastMeetings();
        upcomingMeetings = meetingService.getUpcomingMeetings();
        model.put("allMeetings", allMeetings);
        model.put("pastMeetings", pastMeetings);
        model.put("upcomingMeetings", upcomingMeetings);
        return "home";

    }

    @RequestMapping(value="/home", method = RequestMethod.POST)
    public String viewMeetingSchedule(ModelMap model, @RequestParam String sortSelection){
        allMeetings = meetingService.getMeetings();
        pastMeetings = meetingService.getPastMeetings();
        upcomingMeetings = meetingService.getUpcomingMeetings();
        model.put("allMeetings", allMeetings);
        model.put("pastMeetings", pastMeetings);
        model.put("upcomingMeetings", upcomingMeetings);
        model.put("message", sortSelection);
        return "home";
    }

}
