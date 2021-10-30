package nz.auckland.se754.a6.controller;

import nz.auckland.se754.a6.backend.Database;
import nz.auckland.se754.a6.backend.Meeting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class JoinMeetingController {

    Database db = new Database();
    List<Meeting> meetingList = db.initializeMeetings();
    Map<String, String> currentMeetings = new HashMap<>();

    @RequestMapping(value = "/joinMeeting", method = RequestMethod.GET)
    public String showCurrentMeetings(ModelMap model){
        for (Meeting m: meetingList) {
            currentMeetings.put(m.getMid(),m.getMeetingTitle());
        }
        model.addAttribute("meetings", currentMeetings);
        return "JoinMeeting";
    }

    @RequestMapping(value="/joinMeeting", method = RequestMethod.POST)
    public String joinAMeeting(ModelMap model, @RequestParam String meetingId) {
        if(meetingId.isEmpty()) {
            model.put("message", "You need to enter a meeting id");
        }else{
            boolean hasKey = currentMeetings.containsKey(meetingId);
            if(hasKey) {
                model.put("message", "You have joined the meeting successfully");
            }
            else {
                model.put("message", "The meeting you are trying to join is inactive");
            }
        }

        return showCurrentMeetings(model);
    }
}
