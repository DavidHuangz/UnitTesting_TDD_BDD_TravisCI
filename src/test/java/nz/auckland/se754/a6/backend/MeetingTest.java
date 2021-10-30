package nz.auckland.se754.a6.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;

public class MeetingTest {

    private Database db;
    private Meeting meeting;


    @BeforeEach
    public void setup() {
        db = Mockito.mock(Database.class);
        meeting = new Meeting(db);
    }

    @Test
    @Tag("UnitTest")
    public void testScheduleMeetingToAGroup () {
        List<Meeting> result = new ArrayList<>();
        result.add(meeting);
        Group group = new Group();
        group.addMeeting(meeting);
        assertEquals(result, group.getMeetings());
    }

    @Test
    @Tag("UnitTest")
    public void testGetMeetingId() {
        String mid = "se754";;
        meeting.setMid(mid);
        assertEquals(mid, meeting.getMid());
    }

    @Test
    @Tag("UnitTest")
    public void testGetMeetingTitle() {
        String title = "meeting1";
        meeting.setMeetingTitle(title);
        assertEquals(title, meeting.getMeetingTitle());
    }

    @Test
    @Tag("UnitTest")
    public void testGetMeetingDescription() {
        String description = "group meeting";
        meeting.setMeetingDescription(description);
        assertEquals(description, meeting.getMeetingDescription());
    }

    @Test
    @Tag("UnitTest")
    public void testGetUsers(){
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        meeting.setUsers(list);
        assertEquals(list, meeting.getUsers());
    }

    @Test
    @Tag("UnitTest")
    public void testSetMeetingTime() {
        String start = "13-01-2021 17:09:42";
        String end = "02-02-2021 12:06:22";
        boolean result = meeting.setMeetingTime(start, end);
        assertTrue(result);
        assertEquals(start, meeting.getStartTime());
        assertEquals(end, meeting.getEndTime());
    }

    @Test
    @Tag("UnitTest")
    public void testSetMeetingTimeFail() {
        String start = "13-02-2021 17:09:42";
        String end = "02-02-2021 12:06:22";
        boolean result = meeting.setMeetingTime(start, end);
        assertFalse(result);
    }

    @Test
    @Tag("UnitTest")
    public void testAddUserForMeeting() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        meeting.addUserToMeeting(user1);
        meeting.addUserToMeeting(user2);
        assertEquals(list, meeting.getUsers());
    }

    @Test
    @Tag("UnitTest")
    public void testAddUserFromGroup() {
        Group group = new Group();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        group.setUsers(list);
        meeting.addUserFromGroup(group);
        assertEquals(group.getUsers(), meeting.getUsers());
    }


    @Test
    @Tag("UnitTest")
    public void testRemoveUserFromMeeting() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        Mockito.when(user1.getUid()).thenReturn("id1");
        Mockito.when(user2.getUid()).thenReturn("id2");
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        meeting.setUsers(list);
        boolean result = meeting.removeUserFromMeeting(user1);
        list.remove(user1);
        assertEquals(list, meeting.getUsers());
        assertTrue(result);
    }

    @Test
    @Tag("UnitTest")
    public void testRemoveUserFromMeetingFail() {
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User user3 = Mockito.mock(User.class);
        Mockito.when(user1.getUid()).thenReturn("id1");
        Mockito.when(user2.getUid()).thenReturn("id2");
        Mockito.when(user2.getUid()).thenReturn("id3");
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        meeting.setUsers(list);
        boolean result = meeting.removeUserFromMeeting(user3);
        assertFalse(result);
        assertEquals(list, meeting.getUsers());
    }

    @Test
    @Tag("UnitTest")
    public void testGetAllUserNameForAMeeting () {
        User user1 = new User("name1", "123");
        User user2 = new User("name2", "123");
        user1.setName("name1");
        user2.setName("name2");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        meeting.setUsers(list);
        List<String> result = new ArrayList<>();
        result.add("name1");
        result.add("name2");
        assertEquals(result, meeting.getAllUserNameForAMeeting());
    }

    @Test
    @Tag("UnitTest")
    public void testGetAllUserIdForAMeeting () {
        User user1 = new User("name1", "123");
        User user2 = new User("name2", "123");
        user1.setUid("id1");
        user2.setUid("id2");
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        meeting.setUsers(list);
        List<String> result = new ArrayList<>();
        result.add("id1");
        result.add("id2");
        assertEquals(result, meeting.getAllUserIdForAMeeting());
    }

    @Test
    @Tag("UnitTest")
    public void testSaveMeeting() {
        meeting.setMid("111");
        meeting.setMeetingTitle("se754");
        meeting.setMeetingDescription("meeting1");
        meeting.setMeetingTime("13-01-2021 17:09:42", "02-02-2021 12:06:22");
        List<User> list = new ArrayList<User>();
        meeting.save();
        Mockito.verify(db, times(1)).saveMeeting(meeting);
    }
}
