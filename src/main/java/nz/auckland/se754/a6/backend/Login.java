package nz.auckland.se754.a6.backend;

public class Login {

    public String userLogin(User user, String username, String password) {
        if (checkUsername(user, username)) {
            if (checkPassword(user, password)) {
                user.setLogin();
                return "Successful Login";
            } else {
                return "Invalid password";
            }
        }
        return "Invalid username";
    }

    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public boolean checkUsername(User user, String username) {
        String usernameEmail = user.getUsername() + "aucklanduni.ac.nz";
        return (usernameEmail.equals(username)) || (user.getUsername().equals(username));
    }

    public boolean isUserLoggedIn(User user) {
        return user.getLogin();
    }

    public void userLogout(User user) {
        user.setLogOut();
    }
}
