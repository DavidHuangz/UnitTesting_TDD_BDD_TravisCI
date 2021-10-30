package nz.auckland.se754.a6.controller;

import nz.auckland.se754.a6.backend.Login;
import nz.auckland.se754.a6.backend.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("login")
public class LoginController {
    private final Login login;
    private final User user;

    public LoginController() {
        login = new Login();
        user = new User("abc123", "123");
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password, @RequestParam String newPassword){

        // If reset button not pressed
        if (!newPassword.equals("")) {
            // Reset password
            user.setPassword(newPassword);
            model.put("newPass", "New password: " + newPassword);
            return "login";
            // If submit button pressed
        } else {
            // Get login status
            String status = login.userLogin(user, name, password);
            // Error message if credentials are incorrect
            if (!status.equals("Successful Login")) {
                model.put("errorMessage", status);
                return "login";
            }
            model.put("name", name);
            model.put("password", password);
            return "home";
        }
    }
}
