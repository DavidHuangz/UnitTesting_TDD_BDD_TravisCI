package nz.auckland.se754.a6.webapp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.auckland.se754.a6.webapp.pages.BreakoutGroupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class BreakoutGroupStepDefinitions {
    private WebDriver driver;
    private BreakoutGroupPage page;

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
        page = new BreakoutGroupPage(driver);
    }

    @AfterStep
    public void afterEachStep() {
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

    @Given("I have gone to the breakout group config")
    public void i_have_gone_to_the_breakout_group_config() {
        driver.get("http://localhost:8080/breakout-group");
    }

    @When("I choose to make groups for number of users")
    public void i_choose_to_make_groups_for_number_of_users() {
        page.selectNumberOfUsersOption();
    }

    @Then("I can see the user config parameters")
    public void i_can_see_the_user_config_parameters() {
        assertTrue(page.paramatersAreVisible());
        assertTrue(page.userParametersAreVisible());
    }

    @Then("I can see the group config parameters")
    public void i_can_see_the_group_config_parameters() {
        assertTrue(page.paramatersAreVisible());
        assertTrue(page.groupParametersAreVisible());
    }

    @When("I choose to make groups by number of groups")
    public void i_choose_to_make_groups_by_number_of_groups() {
        page.selectNumberOfGroupsOption();
    }

    @Then("I cannot see the config parameters")
    public void i_cannot_see_the_config_parameters() {
        assertFalse(page.paramatersAreVisible());
        assertFalse(page.userParametersAreVisible());
        assertFalse(page.groupParametersAreVisible());
    }

    @When("I type {int} into the user input")
    public void i_type_into_the_user_input(Integer int1) {
        page.inputNumberOfUsers(int1);
    }

    @Then("The user input has value {int}")
    public void the_user_input_has_value(Integer int1) {
        assertEquals(String.valueOf(int1), page.numberOfUsersValue());
    }

    @When("I type {int} into the group input")
    public void i_type_into_the_group_input(Integer int1) {
        page.inputNumberOfGroups(int1);
    }

    @Then("the group input has value {int}")
    public void the_group_input_has_value(Integer int1) {
        assertEquals(String.valueOf(int1), page.numberOfGroupsValue());
    }

    @When("I click the random user allocation option")
    public void i_click_the_random_user_allocation_option() {
        page.selectRandomUserAllocation();
    }

    @Then("Random allocation format should be selected")
    public void random_allocation_format_should_be_selected() {
        assertTrue(page.randomUserAllocationSetting());
    }

    @When("I click the user choice allocation option")
    public void i_click_the_user_choice_allocation_option() {
        page.selectUserChoiceAllocation();
    }

    @Then("User choice format should be selected")
    public void user_choice_format_should_be_selected() {
        assertTrue(page.userChoiceAllocationSetting());
    }

    @When("I click to make groups")
    public void i_click_to_make_groups() {
        page.makeGroups();
    }

    @Then("I can see an error message for {string}")
    public void i_can_see_an_error_message_for(String string) {
        assertEquals(string, page.getErrorMessage());
    }

    @Then("I can see {int} groups to choose from")
    public void i_can_see_groups_to_choose_from(Integer int1) {
        assertEquals(int1, page.numberOfDisplayedBreakoutGroups());
    }

    @Given("I add {int} users to the meeting")
    public void i_add_users_to_the_meeting(Integer int1) {
        page.addUsersToMeeting(int1);
    }

    @Then("Each group has at least {int} users")
    public void each_group_has_at_least_users(Integer int1) {
        assertTrue(page.groupsHaveAtLeast(int1));
    }

    @Then("Each group has at most {int} users")
    public void each_group_has_at_most_users(Integer int1) {
        assertTrue(page.groupsHaveAtMost(int1));
    }

    @When("I click to join group {int}")
    public void i_click_to_join_group(Integer int1) {
        page.joinGroup(int1);
    }

    @Then("I should see {int} user video stream")
    public void i_should_see_user_video_stream(Integer int1) {
        assertEquals(int1, page.getNumberOfUserStreams());
    }

    @When("I click to disband the group")
    public void i_click_to_disband_the_group() {
        page.disbandGroup();
    }
}
