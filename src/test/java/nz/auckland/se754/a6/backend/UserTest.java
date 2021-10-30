package nz.auckland.se754.a6.backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.Tag;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    @Tag("UnitTest")
    public void testGetCurrentMeetings () {
        User user = new User("abc123", "123");
        Meeting m1 = Mockito.mock(Meeting.class);
        Meeting m2 = Mockito.mock(Meeting.class);
        Meeting m3 = Mockito.mock(Meeting.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime startTime1 = LocalDateTime.now().minusHours(3);
        LocalDateTime startTime2 = LocalDateTime.now().minusHours(2);
        LocalDateTime startTime3 = LocalDateTime.now().minusHours(4);
        LocalDateTime endTime1 = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime2 = LocalDateTime.now().plusHours(2);
        LocalDateTime endTime3 = LocalDateTime.now().minusHours(3);
        Mockito.when(m1.getStartTime()).thenReturn(startTime1.format(formatter));
        Mockito.when(m2.getStartTime()).thenReturn(startTime2.format(formatter));
        Mockito.when(m3.getStartTime()).thenReturn(startTime3.format(formatter));
        Mockito.when(m1.getEndTime()).thenReturn(endTime1.format(formatter));
        Mockito.when(m2.getEndTime()).thenReturn(endTime2.format(formatter));
        Mockito.when(m3.getEndTime()).thenReturn(endTime3.format(formatter));
        List<Meeting> currentMeetings = new ArrayList<>();
        currentMeetings.add(m1);
        currentMeetings.add(m2);
        user.addMeeting(m1);
        user.addMeeting(m2);
        user.addMeeting(m3);
        assertEquals(currentMeetings,user.getCurrentMeetings());
    }

    @Test
    @Tag("UnitTest")
    public void testJoinAMeeting() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        User user = new User("abc123", "123");
        Meeting m1 = Mockito.mock(Meeting.class);

        LocalDateTime now = LocalDateTime.now();
        String start_time = now.minusHours(1).format(formatter);
        String end_time = now.plusHours(1).format(formatter);

        Mockito.when(m1.getStartTime()).thenReturn(start_time);
        Mockito.when(m1.getEndTime()).thenReturn(end_time);

        user.addMeeting(m1);
        assertEquals(m1, user.joinAMeeting(m1));
    }

    @Test
    @Tag("UnitTest")
    public void testJoinAInvalidMeeting() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        User user = new User("abc123", "123");
        Meeting m1 = Mockito.mock(Meeting.class);

        LocalDateTime now = LocalDateTime.now();
        String start_time = now.plusHours(1).format(formatter);

        String end_time = now.plusHours(2).format(formatter);

        Mockito.when(m1.getStartTime()).thenReturn(start_time);
        Mockito.when(m1.getEndTime()).thenReturn(end_time);

        try{
            user.joinAMeeting(m1);
        } catch(UnsupportedOperationException e) {
            assertEquals("The meeting you are looking for is not active", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testGetAllMeetingsForAUser () {
        User user = new User("abc123","123");
        Meeting m1 = Mockito.mock(Meeting.class);
        Meeting m2 = Mockito.mock(Meeting.class);
        List<Meeting> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        user.addMeeting(m1);
        user.addMeeting(m2);
        assertEquals(list, user.getAllMeetingsForAUser());
    }

    @Test
    @Tag("UnitTest")
    public void testGetUpcomingMeetingsForAUser () {
        User user = new User("abc123","123");
        Meeting m1 = Mockito.mock(Meeting.class);
        Meeting m2 = Mockito.mock(Meeting.class);
        Meeting m3 = Mockito.mock(Meeting.class);
        Mockito.when(m1.getStartTime()).thenReturn("13-01-2021 17:09:42");
        Mockito.when(m2.getStartTime()).thenReturn("19-05-2122 11:09:00");
        Mockito.when(m3.getStartTime()).thenReturn("13-01-2033 17:39:09");
        List<Meeting> list = new ArrayList<>();
        list.add(m2);
        list.add(m3);
        user.addMeeting(m1);
        user.addMeeting(m2);
        user.addMeeting(m3);
        assertEquals(list, user.getUpcomingMeetings());
    }

    @Test
    @Tag("UnitTest")
    public void testGetPastMeetingsForAUser () {
        User user = new User("abc123","123");
        Meeting m1 = Mockito.mock(Meeting.class);
        Meeting m2 = Mockito.mock(Meeting.class);
        Meeting m3 = Mockito.mock(Meeting.class);
        Mockito.when(m1.getEndTime()).thenReturn("13-01-2021 17:09:42");
        Mockito.when(m2.getEndTime()).thenReturn("19-05-2122 11:09:00");
        Mockito.when(m3.getEndTime()).thenReturn("13-01-2033 17:39:09");
        List<Meeting> list = new ArrayList<>();
        list.add(m1);
        user.addMeeting(m1);
        user.addMeeting(m2);
        user.addMeeting(m3);
        assertEquals(list, user.getPastMeetings());
    }

    @Test
    @Tag("UnitTest")
    public void testGetMeetingsForSpecificDate (){
        User user = new User("abc123","123");
        Meeting m1 = Mockito.mock(Meeting.class);
        Meeting m2 = Mockito.mock(Meeting.class);
        Meeting m3 = Mockito.mock(Meeting.class);
        Meeting m4 = Mockito.mock(Meeting.class);
        Meeting m5 = Mockito.mock(Meeting.class);
        Mockito.when(m1.getStartTime()).thenReturn("13-01-2021 17:09:42");
        Mockito.when(m2.getStartTime()).thenReturn("13-01-2021 11:09:00");
        Mockito.when(m3.getStartTime()).thenReturn("22-07-2033 17:39:09");
        Mockito.when(m4.getStartTime()).thenReturn("22-01-2021 17:39:09");
        Mockito.when(m5.getStartTime()).thenReturn("13-05-2021 17:39:09");
        String date = "13-01-2021";
        List<Meeting> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        user.addMeeting(m1);
        user.addMeeting(m2);
        user.addMeeting(m3);
        user.addMeeting(m4);
        user.addMeeting(m5);
        assertEquals(list, user.getMeetingsForSpecificDate(date));
    }
}
