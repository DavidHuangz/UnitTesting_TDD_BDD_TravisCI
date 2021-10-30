package nz.auckland.se754.a6.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTest {
    Database db;

    @BeforeEach
    public void setupEach() {
        db = new Database();
    }

    @Test
    @Tag("IntegrationTest")
    public void UserLoginCreateGroup() {
        // User (Organiser) correctly logging in
        Login login = new Login();
        User user = new User("abc123", "123");
        login.userLogin(user, "abc123aucklanduni.ac.nz", "123");
        assertTrue(login.isUserLoggedIn(user));

        // Group setup
        GroupHandler groupHandler = new GroupHandler(db);
        Group newGroup;

        // Add other users
        List<User> _allUsers = new ArrayList<>();
        User user2 = new User("abc432", "123");
        User user3 = new User("abc321", "123");
        _allUsers.add(user2);
        _allUsers.add(user3);

        // Create group
        newGroup = groupHandler.createGroup(user,"StudyRoom", _allUsers);
        assertEquals("StudyRoom", newGroup.getGroupName());
        assertEquals(user, newGroup.getGroupOwner());
        assertEquals(_allUsers, newGroup.getUsers());

        // Save Group
        groupHandler.save(newGroup);
        db.groupSave(newGroup);
        assertEquals("StudyRoom", db.getGroup(0).getGroupName());
        assertEquals(user, db.getGroup(0).getGroupOwner());
        assertEquals(_allUsers, db.getGroup(0).getUsers());
    }

    @Test
    @Tag("IntegrationTest")
    public void testViewUpcomingMeetings () {
        //create a user
        User user = new User("abby", "123");

        //create a group
        GroupHandler groupHandler = new GroupHandler(db);
        List<User> _allUsers = new ArrayList<>();
        _allUsers.add(user);
        Group newGroup = groupHandler.createGroup(user, "math_group", _allUsers);

        //create a meeting
        Meeting meeting = new Meeting(db);
        meeting.setMid("111");
        meeting.setMeetingTitle("se754");
        meeting.setMeetingDescription("meeting1");
        meeting.setMeetingTime("02-02-3023 12:06:22", "02-02-3024 12:06:22");
        meeting.save();

        //add the meeting to all the users in the group
        newGroup.addMeeting(meeting);

        List<Meeting> expectedResults = new ArrayList<>();
        expectedResults.add(meeting);
        assertEquals(expectedResults, user.getUpcomingMeetings());
        assertEquals(meeting, db.getMeeting(0));
    }

    @Test
    @Tag("IntegrationTest")
    public void testJoinMeetingAndMakeGroups() {
        Meeting meeting = new Meeting(db);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String start_time = now.minusHours(1).format(formatter);
        String end_time = now.plusHours(2).format(formatter);

        meeting.setMeetingTime(start_time, end_time);

        User user1 = new User("user1", "pass");
        User user2 = new User("user2", "pass");

        user1.addMeeting(meeting);
        user2.addMeeting(meeting);
        user1.joinAMeeting(meeting);
        user2.joinAMeeting(meeting);

        meeting.getGroupHandler().makeNumberOfBreakoutGroups(1, true);
        List<BreakoutGroup> groups = meeting.getGroupHandler().getBreakoutGroups();

        assertEquals(1, groups.size());
        assertTrue(groups.get(0).getUsers().containsAll(List.of(user1, user2)));
    }
}
