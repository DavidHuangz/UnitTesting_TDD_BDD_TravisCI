package nz.auckland.se754.a6.webapp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.auckland.se754.a6.webapp.pages.JoinMeetingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JoinMeetingStepDefinitions {

    private WebDriver driver;
    private JoinMeetingPage joinMeetingPage;
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
        joinMeetingPage = new JoinMeetingPage(driver);

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

    @Given("I visit the page for joining a meeting")
    public void i_visit_the_page_for_joining_a_meeting() {
        driver.get("http://localhost:8080/joinMeeting");
    }

    @When("I enter {string} as meeting ID field to join a meeting")
    public void i_enter_as_meeting_ID_field_to_join_a_meeting(String string) {
        joinMeetingPage.insertMeetingID(string);
    }

    @When("I press the join button")
    public void i_press_the_join_button() {
        joinMeetingPage.clickJoin();
    }

    @Then("I should see a message for joining the meeting")
    public void i_should_see_a_message_for_joining_the_meeting() {
        assertTrue(joinMeetingPage.getMessage().contains("You have joined the meeting successfully"));
    }

    @Then("I should see an error message for inactive meeting")
    public void i_should_see_an_error_message_for_inactive_meeting() {
        assertTrue(joinMeetingPage.getMessage().contains("The meeting you are trying to join is inactive"));
    }

    @Then("I should see an error message for not entering a meeting id")
    public void i_should_see_an_error_message_for_not_entering_a_meeting_id() {
        assertTrue(joinMeetingPage.getMessage().contains("You need to enter a meeting id"));
    }

    @Then("I should be able to see all active meetings")
    public void i_should_be_able_to_see_all_active_meetings() {
        List<String> expected_id = Arrays.asList("ID: 001", "ID: 002", "ID: 003", "ID: 004");
        List<String> expected_title = Arrays.asList("Title: Meeting1","Title: Meeting2","Title: Meeting3","Title: Meeting4");
        List<String> resultId = joinMeetingPage.getMeetingId();
        List<String> resultTitle = joinMeetingPage.getMeetingTitle();

        for(int i = 0; i<expected_id.size(); i++) {
            assertEquals(expected_id.get(i),resultId.get(i));
        }

        for(int i = 0; i<expected_title.size(); i++ ) {
            assertEquals(expected_title.get(i), resultTitle.get(i));
        }
    }
}
