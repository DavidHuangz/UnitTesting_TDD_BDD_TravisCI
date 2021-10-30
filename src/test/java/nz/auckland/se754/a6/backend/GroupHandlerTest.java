package nz.auckland.se754.a6.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

public class GroupHandlerTest {

    private Database db;
    private GroupHandler groupHandler;

    @BeforeEach
    public void setup() {
        db = Mockito.mock(Database.class);
        groupHandler = new GroupHandler(db);
    }

    @Test
    @Tag("UnitTest")
    public void testCreateGroup() {
        List<User> _allUsers = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User user3 = Mockito.mock(User.class);
        _allUsers.add(user1);
        _allUsers.add(user2);
        Group newGroup;
        newGroup = groupHandler.createGroup(user3,"math_group", _allUsers);
        assertEquals("math_group", newGroup.getGroupName());
        assertEquals(user3, newGroup.getGroupOwner());
        assertEquals(_allUsers, newGroup.getUsers());
    }

    @Test
    @Tag("UnitTest")
    public void giveUserFeedbackForMissingGroupName() {

        List<User> _allUsers = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        _allUsers.add(user1);

        try{
            groupHandler.createGroup(user2,"", _allUsers);
            fail();
        } catch(UnsupportedOperationException e) {
            assertEquals("Your group has not been created because of missing group name input", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void giveUserFeedbackForMissingGroupOwner() {
        List<User> _allUsers = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        _allUsers.add(user1);

        try{
            groupHandler.createGroup(null,"math_group", _allUsers);
            fail();
        } catch(UnsupportedOperationException e) {
            assertEquals("Your group has not been created because it needs to have at least one member", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testAddUser () {
        List<User> _allUsers = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User user3 = Mockito.mock(User.class);
        _allUsers.add(user1);
        _allUsers.add(user2);
        groupHandler.addUser(user3,_allUsers);
        assertEquals(user3,_allUsers.get(2));
    }

    @Test
    @Tag("UnitTest")
    public void testRemoveUser () {
        List<User> _allUsers = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        _allUsers.add(user1);
        _allUsers.add(user2);
        Mockito.when(user1.getUsername()).thenReturn("Amy");
        groupHandler.removeUser(user1, _allUsers);
        assertEquals(user2, _allUsers.get(0));
    }

    @Test
    @Tag("UnitTest")
    public void testRemoveInvalidUser () {
        List<User> _allUsers = new ArrayList<>();
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User user3 = Mockito.mock(User.class);
        _allUsers.add(user1);
        _allUsers.add(user2);
        Mockito.when(user3.getUsername()).thenReturn("Amy");
        groupHandler.removeUser(user3, _allUsers);
        assertEquals(user1, _allUsers.get(0));
        assertEquals(user2, _allUsers.get(1));
    }

    @Test
    @Tag("UnitTest")
    public void testSaveGroup() {
        List<User> _allUsers = new ArrayList<>();
        Group newGroup;
        User user1 = Mockito.mock(User.class);
        User user2 = Mockito.mock(User.class);
        User user3 = Mockito.mock(User.class);
        _allUsers.add(user1);
        _allUsers.add(user2);
        newGroup = groupHandler.createGroup(user3,"math_group", _allUsers);
        groupHandler.save(newGroup);
        Mockito.verify(db, times(1)).saveGroup(user3,"math_group",_allUsers);
    }
}
