package nz.auckland.se754.a6.webapp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.auckland.se754.a6.webapp.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepDefinitions {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            System.setProperty("webdriver.chrome.driver", "webdrivers/win/chromedriver.exe");
        } else{
            System.setProperty("webdriver.chrome.driver", "webdrivers/macos/chromedriver");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
    }

    @AfterStep
    public void afterEach() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I visit {string}")
    public void i_visit(String string) {
        driver.get("http://localhost:8080" + string);

    }

    @When("I enter {string} as user name field")
    public void i_enter_as_user_name_field(String string) {
        loginPage.insertUserName(string);
    }

    @When("I enter {string} as password field")
    public void i_enter_as_password_field(String string) {
        loginPage.insertPassword(string);
    }

    @When("I press the submit button")
    public void i_press_the_submit_button() {
        loginPage.clickLogin();
    }

    @When("I enter {string} as new password field")
    public void i_enter_as_new_password_field(String string) {
        loginPage.insertNewPassword(string);
    }

    @When("I press the Reset button")
    public void i_press_the_reset_button() {
        loginPage.clickReset();
    }

    @When("I press the press the logout button")
    public void i_press_the_logout_button() {
        loginPage.clickLogout();
    }

    @Then("I should see the home page")
    public void i_should_see_the_welcome_page() {
        assertTrue(loginPage.getHomePageMessage().contains("Home Page"));
    }

    @Then("I should be in the login page")
    public void i_should_be_in_the_login_page() {
        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/"));
    }

    @Then("I should see the username error message")
    public void i_should_see_the_username_error_message() {
        assertTrue(loginPage.getError().contains("Invalid username"));
    }

    @Then("I should see the password error message")
    public void i_should_see_the_password_error_message() {
        assertTrue(loginPage.getError().contains("Invalid password"));
    }
}