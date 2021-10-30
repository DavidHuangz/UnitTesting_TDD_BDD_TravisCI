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

public class ScheduleAMeetingStepDefinitions {

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

    @Given("I am on the home page")
    public void i_have_opened_the_home_page() {
        driver.get("http://localhost:8080/home");
    }

    @When("I click the button for creating a meeting")
    public void i_click_on_create_a_meeting() {
        driver.findElement(By.id("createMeetingBtn")).click();
    }

    @Then("I am taken to the page for scheduling a meeting")
    public void i_am_taken_to_create_meeting_page() {
        String expectedResult = "http://localhost:8080/createMeeting";
        String url = driver.getCurrentUrl();
        assertEquals(url, expectedResult);
    }

    @Given("I am on the create meeting page")
    public void i_have_opened_the_create_meeting_page() {
        driver.get("http://localhost:8080/createMeeting");
    }

    @When("I have entered the meeting ID {string}")
    public void i_enter_meeting_id(String string) {
        driver.findElement(By.id("meetingID")).sendKeys(string);
    }

    @When("I have entered the meeting title {string}")
    public void i_enter_meeting_title(String string) {
        driver.findElement(By.id("meetingTitle")).sendKeys(string);
    }

    @When("I have entered the meeting description {string}")
    public void i_enter_meeting_description(String string) {
        driver.findElement(By.id("meetingDescription")).sendKeys(string);
    }

    @When("I have set the start date")
    public void i_set_meeting_start_date() {
        driver.findElement(By.id("startDate")).sendKeys("28-05-2021");
    }

    @When("I have set the start time")
    public void i_set_meeting_start_time() {
        driver.findElement(By.id("startTime")).sendKeys("1000AM");
    }

    @When("I have set the end date")
    public void i_set_meeting_end_date() {
        driver.findElement(By.id("endDate")).sendKeys("28-05-2021");
    }

    @When("I have set the end time")
    public void i_set_meeting_end_time() {
        driver.findElement(By.id("endTime")).sendKeys("0200PM");
    }

    @When("I choose the group {string}")
    public void i_choose_group(String string){
        driver.findElement(By.name(string)).click();
    }

    @When("I click the submit button")
    public void i_click_submit_button() {
        driver.findElement(By.id("submitCreateMeetingBtn")).click();
    }

    @Then("I can see create group message")
    public void i_can_see_success_message_for_creating_a_meeting() {
        driver.findElement(By.id("message")).getText().contains("The meeting has been created");
    }

    @When("I have set a invalid end date")
    public void i_set_invalid_meeting_end_date() {
        driver.findElement(By.id("endDate")).sendKeys("28-04-2021");
    }

    @When("I have set a invalid end time")
    public void i_set_invalid_meeting_end_time() {
        driver.findElement(By.id("endTime")).sendKeys("0800AM");
    }

    @Then("I should see the invalid time message")
    public void i_can_see_invalid_meeting_time_message() {
        driver.findElement(By.id("message")).getText().contains("Invalid Meeting Time!");
    }

    @Then("I should see the invalid meeting id message")
    public void i_can_see_invalid_meeting_id_message() {
        driver.findElement(By.id("message")).getText().contains("You need to enter the meeting ID!");
    }

    @Then("I should see the empty meeting time message")
    public void i_can_see_empty_meeting_time_message() {
        driver.findElement(By.id("message")).getText().contains("You need to choose the meeting time!");
    }

    @Then("I can see the meeting ID is set as {string}")
    public void i_can_see_meeting_ID_is_set(String string) {
        assertEquals(driver.findElement(By.id("meetingID")).getAttribute("value"), string);
    }

    @Then("I can see the meeting title is set as {string}")
    public void i_can_see_meeting_title_is_set(String string) {
        assertEquals(driver.findElement(By.id("meetingTitle")).getAttribute("value"), string);
    }

    @Then("I can see the meeting description is set as {string}")
    public void i_can_see_meeting_description_is_set(String string) {
        assertEquals(driver.findElement(By.id("meetingDescription")).getAttribute("value"), string);
    }

    @Then("I can see the meeting start date is set")
    public void i_can_see_meeting_start_date_is_set() {
        assertEquals(driver.findElement(By.id("startDate")).getAttribute("value"), "2021-05-28");
    }

    @Then("I can see the meeting start time is set")
    public void i_can_see_meeting_start_time_is_set() {
        assertEquals(driver.findElement(By.id("startTime")).getAttribute("value"), "10:00");
    }

    @Then("I can see the meeting end date is set")
    public void i_can_see_meeting_end_date_is_set() {
        assertEquals(driver.findElement(By.id("EndDate")).getAttribute("value"), "2021-05-28");
    }

    @Then("I can see the meeting end time is set")
    public void i_can_see_meeting_end_time_is_set() {
        assertEquals(driver.findElement(By.id("endTime")).getAttribute("value"), "14:00");
    }

    @Then("I can see the group is set as {string}")
    public void i_can_see_meeting_group_is_set(String string) {
        assertEquals(driver.findElement(By.name("chooseGroup")).getAttribute("value"), string);
    }

    @When("I choose recurring as true")
    public void i_choose_recurring_true(){
        driver.findElement(By.name("recurringTrue")).click();
    }

    @Then("I can see the recurring is set as true")
    public void i_can_see_recurring_is_set_as_true() {
        assertEquals(driver.findElement(By.name("recurring")).getAttribute("value"), "recurringTrue");
    }


}
