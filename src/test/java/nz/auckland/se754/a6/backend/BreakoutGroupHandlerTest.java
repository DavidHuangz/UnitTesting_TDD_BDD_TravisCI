package nz.auckland.se754.a6.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BreakoutGroupHandlerTest {
    private BreakoutGroupHandler handler;
    private List<User> users;

    @BeforeEach
    public void setupEach() {
        User u1 = Mockito.mock(User.class);
        User u2 = Mockito.mock(User.class);
        User u3 = Mockito.mock(User.class);
        User u4 = Mockito.mock(User.class);
        User u5 = Mockito.mock(User.class);
        User u6 = Mockito.mock(User.class);
        User u7 = Mockito.mock(User.class);
        User u8 = Mockito.mock(User.class);
        User u9 = Mockito.mock(User.class);

        users = new ArrayList<>(List.of(u1, u2, u3, u4, u5, u6, u7, u8, u9));
        handler = new BreakoutGroupHandler(users);
    }

    @Test
    @Tag("UnitTest")
    public void testInitialiseWithDefaultGroup() {
        BreakoutGroupHandler handler = new BreakoutGroupHandler(users);
        List<User> main_group = handler.getMainGroup();

        assertEquals(users.size(), main_group.size());
        assertTrue(main_group.containsAll(users));
    }

    @Test
    @Tag("UnitTest")
    public void testMakeSingleBreakoutGroup() {
        List<BreakoutGroup> groups = handler.makeNumberOfBreakoutGroups(1, true);
        assertEquals(1, groups.size());

        BreakoutGroup group = groups.get(0);
        List<User> group_users = group.getUsers();

        assertTrue(group_users.containsAll(users));
    }

    @Test
    @Tag("UnitTest")
    public void testMakeMultipleBreakoutGroups() {
        List<BreakoutGroup> groups = handler.makeNumberOfBreakoutGroups(2, true);
        assertEquals(2, groups.size());

        List<User> group1 = groups.get(0).getUsers();
        assertEquals(5, group1.size());

        List<User> group2 = groups.get(1).getUsers();
        assertEquals(4, group2.size());

        List<User> all_users = new ArrayList<>();
        all_users.addAll(group1);
        all_users.addAll(group2);

        assertTrue(all_users.containsAll(users));
    }

    @Test
    @Tag("UnitTest")
    public void testMakingNumberOfBreakoutGroupsRemovesUsersFromMainGroup() {
        handler.makeNumberOfBreakoutGroups(1, true);
        List<User> main_group = handler.getMainGroup();

        assertEquals(0, main_group.size());
    }

    @Test
    @Tag("UnitTest")
    public void testMakeGroupsForNumberOfUsers() {
        List<BreakoutGroup> groups = handler.makeBreakoutGroupsForNumberOfUsers(3, true);
        assertEquals(3, groups.size());

        List<User> user_record = new ArrayList<>();
        for (BreakoutGroup group : groups) {
            assertEquals(3, group.getUsers().size());

            user_record.addAll(group.getUsers());
        }

        assertTrue(user_record.containsAll(users));
    }

    @Test
    @Tag("UnitTest")
    public void testMakeGroupsForNumberOfUsersRemovesThemFromMainGroup() {
        handler.makeBreakoutGroupsForNumberOfUsers(3, true);
        List<User> main_group = handler.getMainGroup();

        assertEquals(0, main_group.size());
    }

    @Test
    @Tag("UnitTest")
    public void testCanDisbandBreakoutGroup() {
        final int NUM_USERS_PER_GROUP = 3;

        handler.makeBreakoutGroupsForNumberOfUsers(NUM_USERS_PER_GROUP, true);
        handler.disbandBreakoutGroup(0);

        List<BreakoutGroup> groups = handler.getBreakoutGroups();
        assertEquals(2, groups.size());

        for (BreakoutGroup group : groups) {
            assertEquals(NUM_USERS_PER_GROUP, group.getUsers().size());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testErrorForOutOfBoundsGroupDisband() {
        try {
            handler.makeNumberOfBreakoutGroups(1, true);
            handler.disbandBreakoutGroup(1);

            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals("No corresponding breakout group", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testDisbandingBreakoutGroupAddsUsersBackToMainGroup() {
        final int NUM_USERS_PER_GROUP = 3;

        handler.makeBreakoutGroupsForNumberOfUsers(NUM_USERS_PER_GROUP, true);
        handler.disbandBreakoutGroup(0);

        List<User> main_group = handler.getMainGroup();
        assertEquals(NUM_USERS_PER_GROUP, main_group.size());
    }

    @Test
    @Tag("UnitTest")
    public void testMakeNumberOfEmptyBreakoutGroups() {
        List<BreakoutGroup> empty_groups = handler.makeNumberOfBreakoutGroups(3, false);
        assertEquals(3, empty_groups.size());

        for (BreakoutGroup group : empty_groups) {
            assertEquals(0, group.getUsers().size());
        }

        List<User> main_group = handler.getMainGroup();
        assertEquals(9, main_group.size());
    }

    @Test
    @Tag("UnitTest")
    public void testMakeEmptyBreakoutGroupsForNumberOfUsers() {
        List<BreakoutGroup> empty_groups = handler.makeBreakoutGroupsForNumberOfUsers(3, false);
        assertEquals(3, empty_groups.size());

        for (BreakoutGroup group : empty_groups) {
            assertEquals(0, group.getUsers().size());
        }

        List<User> main_group = handler.getMainGroup();
        assertEquals(9, main_group.size());
    }

    @Test
    @Tag("UnitTest")
    public void testAddUserToEmptyGroup() {
        User user = Mockito.mock(User.class);

        handler.makeNumberOfBreakoutGroups(1, false);
        handler.addUserToBreakoutGroup(user, 0);

        List<BreakoutGroup> groups = handler.getBreakoutGroups();
        List<User> group_users = groups.get(0).getUsers();

        assertEquals(1, group_users.size());
        assertEquals(user, group_users.get(0));
    }

    @Test
    @Tag("UnitTest")
    public void testRemoveUserFromGroup() {
        User user = Mockito.mock(User.class);

        handler.makeNumberOfBreakoutGroups(1, false);
        handler.addUserToBreakoutGroup(user, 0);
        handler.removeUserFromBreakoutGroup(user, 0);

        List<BreakoutGroup> groups = handler.getBreakoutGroups();
        List<User> group_users = groups.get(0).getUsers();

        assertEquals(0, group_users.size());
    }

    @Test
    @Tag("UnitTest")
    public void testErrorWhenAddUserToFullGroup() {
        try {
            User user = Mockito.mock(User.class);

            List<BreakoutGroup> groups = handler.makeBreakoutGroupsForNumberOfUsers(1, true);
            handler.addUserToBreakoutGroup(user, 0);

            fail();
        } catch(UnsupportedOperationException e) {
            assertEquals("Breakout group is full", e.getMessage());
        }
    }

    @Test
    @Tag("UnitTest")
    public void testCanGetBreakoutGroupsWithSpaceForMoreUsers() {
        handler.makeBreakoutGroupsForNumberOfUsers(5, true);
        List<BreakoutGroup> groups = handler.getAvailableBreakoutGroups();

        assertEquals(1, groups.size());
    }

    @Test
    @Tag("UnitTest")
    public void testMakingNumberOfBreakoutGroupsRandomisesUsers() {
        List<BreakoutGroup> groups1 = handler.makeNumberOfBreakoutGroups(2, true);

        handler.disbandBreakoutGroup(0);
        handler.disbandBreakoutGroup(0);

        List<BreakoutGroup> groups2 = handler.makeNumberOfBreakoutGroups(2, true);

        assertNotEquals(groups1.get(0).getUsers(), groups2.get(0).getUsers());
        assertNotEquals(groups1.get(1).getUsers(), groups2.get(1).getUsers());
    }

    @Test
    @Tag("UnitTest")
    public void testMakingBreakoutGroupsForNumberOfUsersRandomisesUsers() {
        List<BreakoutGroup> groups1 = handler.makeBreakoutGroupsForNumberOfUsers(3, true);

        handler.disbandBreakoutGroup(0);
        handler.disbandBreakoutGroup(0);
        handler.disbandBreakoutGroup(0);

        List<BreakoutGroup> groups2 = handler.makeBreakoutGroupsForNumberOfUsers(3, true);

        assertNotEquals(groups1.get(0).getUsers(), groups2.get(0).getUsers());
        assertNotEquals(groups1.get(1).getUsers(), groups2.get(1).getUsers());
        assertNotEquals(groups1.get(2).getUsers(), groups2.get(2).getUsers());
    }
}
