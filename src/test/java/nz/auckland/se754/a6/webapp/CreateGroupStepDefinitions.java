package nz.auckland.se754.a6.webapp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.auckland.se754.a6.webapp.pages.CreateGroupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateGroupStepDefinitions {

    private WebDriver driver;
    private WebDriver element;
    private CreateGroupPage createGroupPage;

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
        createGroupPage = new CreateGroupPage(driver);
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

    @Given("I visit the group page")
    public void i_visit() {
        driver.get("http://localhost:8080/group");
    }

    @When("I enter {string} as group name field")
    public void i_enter_as_group_name_field(String string) {
        createGroupPage.insertGroupName(string);
    }

    @When("I enter {string} as user name field for adding group members")
    public void i_enter_as_user_name_field_for_adding_group_members(String string) {
        createGroupPage.insertUserName(string);
    }

    @When("I press the create button")
    public void i_press_the_create_button() {
        createGroupPage.clickCreate();
    }

    @Then("I should see the group is created successfully message")
    public void i_should_see_the_group_is_created_successfully_message() {
        assertTrue(createGroupPage.getMessage().contains("Your group has been created"));
    }

    @Then("I should see an error message for invalid group name")
    public void i_should_see_an_error_message_for_invalid_group_name() {
        assertTrue(createGroupPage.getMessage().contains("Group can't be created without a group name"));
    }

    @Then("I should see an error message for invalid user")
    public void i_should_see_an_error_message_for_invalid_user() {
        assertTrue(createGroupPage.getMessage().contains("Group can't be created without a user"));
    }

    @Given("I visit the group configuration page")
    public void i_visit_the_group_configuration_page() {
        driver.get("http://localhost:8080/group-configuration");
    }

    @When("I enter {string} in the user name field")
    public void i_enter_in_the_user_name_field(String string) {
        createGroupPage.insertUserName(string);
    }

    @When("I press the add button")
    public void i_press_the_and_button() {
        createGroupPage.clickAdd();
    }

    @Then("I should see a message for adding user successfully")
    public void i_should_see_a_message_for_adding_user_successfully() {
        createGroupPage.getMessage().contains("The user has been added");
    }

    @Then("I should see an error message for invalid user name")
    public void i_should_see_an_error_message_for_invalid_user_name() {
        createGroupPage.getMessage().contains("You need to enter the username to add/remove a group member");
    }

    @When("I press the remove button")
    public void i_press_the_remove_button() {
        createGroupPage.clickRemove();
    }

    @Then("I should see a message for removing user successfully")
    public void i_should_see_a_message_for_removing_user_successfully() {
        createGroupPage.getMessage().contains("The user has been removed");
    }

}
