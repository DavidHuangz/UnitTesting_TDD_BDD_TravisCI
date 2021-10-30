package nz.auckland.se754.a6.webapp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;


public class ViewMeetingScheduleStepDefinitions {
    private WebDriver driver;

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

    @Given("I visit the home page for viewing meeting schedule")
    public void i_have_opened_the_home_page() {
        driver.get("http://localhost:8080/home");
    }

    @When("I choose view all the meetings")
    public void i_choose_view_all_meeting() {
        new Select(driver.findElement(By.name("sortSelection"))).selectByVisibleText("All");
    }

    @Then("I should see the all meetings message")
    public void i_should_see_all_meetings_message() {
        assertTrue(driver.findElement(By.id("message")).getText().contains("All"));
    }

    @When("I choose view past meetings")
    public void i_choose_view_past_meeting() {
        new Select(driver.findElement(By.name("sortSelection"))).selectByVisibleText("Past");
    }

    @Then("I should see the past meetings message")
    public void i_should_see_past_meetings_message() {
        assertTrue(driver.findElement(By.id("message")).getText().contains("Past"));
    }

    @When("I choose view upcoming meetings")
    public void i_choose_view_upcoming_meeting() {
        new Select(driver.findElement(By.name("sortSelection"))).selectByVisibleText("Upcoming");
    }

    @Then("I should see the upcoming meetings message")
    public void i_should_see_upcoming_meetings_message() {
        assertTrue(driver.findElement(By.id("message")).getText().contains("Upcoming"));
    }

    @Then("I can see meeting ID")
    public void i_should_see_meeting_ID() {
        assertNotEquals(driver.findElement(By.id("meetingID")).getText(), "");
    }

    @Then("I can see meeting title")
    public void i_should_see_meeting_title() {
        assertNotEquals(driver.findElement(By.id("meetingTitle")).getText(), "");
    }

    @Then("I can see meeting description")
    public void i_should_see_meeting_description() {
        assertNotEquals(driver.findElement(By.id("meetingDescription")).getText(), "");
    }

    @Then("I can see meeting time")
    public void i_should_see_meeting_time() {
        assertNotEquals(driver.findElement(By.id("meetingTime")).getText(), "");
    }
    @Then("I can see user names")
    public void i_should_see_user_names() {
        assertNotEquals(driver.findElement(By.id("Usernames")).getText(), "");
    }

}
