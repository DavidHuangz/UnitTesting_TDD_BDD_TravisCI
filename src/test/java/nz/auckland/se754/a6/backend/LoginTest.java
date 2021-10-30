package nz.auckland.se754.a6.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private Login login;
    private User user;

    @BeforeEach
    public void setup() {
        login = new Login();
        user = new User("abc123", "123");
    }

    @Test
    @Tag("UnitTest")
    public void usersLoggedInShouldReturnFalse() {
        assertFalse(login.isUserLoggedIn(user));
    }

    @Test
    @Tag("UnitTest")
    public void userCanLoginWithUOAAccountReturnTrue() {
        login.userLogin(user, "abc123", "123");
        assertTrue(login.isUserLoggedIn(user));
    }

    @Test
    @Tag("UnitTest")
    public void userCanLoginWithUOAAccountReturnFalse() {
        login.userLogin(user, "abc1234", "1234");
        assertFalse(login.isUserLoggedIn(user));
    }

    @Test
    @Tag("UnitTest")
    public void userCanLoginWithUOAEmailAccountReturnTrue() {
        login.userLogin(user, "abc123aucklanduni.ac.nz", "123");
        assertTrue(login.isUserLoggedIn(user));
    }

    @Test
    @Tag("UnitTest")
    public void giveUserFeedbackForNotLoggingInReturnInvalidUsername() {
        String output = login.userLogin(user, "abc1234", "123");
        assertEquals(output, "Invalid username");
    }

    @Test
    @Tag("UnitTest")
    public void giveUserFeedbackForNotLoggingInReturnInvalidPassword() {
        String output = login.userLogin(user, "abc123", "1234");
        assertEquals(output, "Invalid password");
    }

    @Test
    @Tag("UnitTest")
    public void giveUserFeedbackForLoggingInReturnSuccessfulLogin() {
        String output = login.userLogin(user, "abc123", "123");
        assertEquals(output, "Successful Login");
    }

    @Test
    @Tag("UnitTest")
    public void checkUsername() {
        assertTrue(login.checkUsername(user, "abc123aucklanduni.ac.nz"));
    }

    @Test
    @Tag("UnitTest")
    public void checkUsernameFalse() {
        assertFalse(login.checkUsername(user, "abc1234"));
    }

    @Test
    @Tag("UnitTest")
    public void checkUsernameEmail() {
        assertTrue(login.checkUsername(user, "abc123aucklanduni.ac.nz"));
    }

    @Test
    @Tag("UnitTest")
    public void checkPassword() {
        assertTrue(login.checkPassword(user, "123"));
    }

    @Test
    @Tag("UnitTest")
    public void giveUserToChangePassword() {
        user.setPassword("changedPassword");
        assertEquals(user.getPassword(), "changedPassword");
    }

    @Test
    @Tag("UnitTest")
    public void userCanLogOut() {
        login.userLogin(user, "abc123", "123");
        login.userLogout(user);
        assertFalse(login.isUserLoggedIn(user));
    }
}

