package nz.auckland.se754.a6.webapp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.auckland.se754.a6.webapp.pages.CardActivityConfigPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CardActivityConfigStepDefinitions {

    private WebDriver driver;
    private CardActivityConfigPage page;

    @Before
    public void setup() {
        if (System.getProperty("os.name").startsWith("Windows")){
            System.setProperty("webdriver.chrome.driver", "webdrivers/win/chromedriver.exe");
        } else{
            System.setProperty("webdriver.chrome.driver", "webdrivers/macos/chromedriver");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        page = new CardActivityConfigPage(driver);
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

    @Given("I have gone to the new activity page")
    public void i_have_gone_to_the_new_activity_page() {
        driver.get("http://localhost:8080/card-activity");
    }

    @Then("I should see the card values that will be used")
    public void i_should_see_the_card_values_that_will_be_used() {
        List<String> expected_values = Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K");

        for (String value : expected_values) {
            String actual_value = page.getValue(value);
            assertEquals(value, actual_value);
        }
    }

    @Then("I should see the card suits that will be used")
    public void i_should_see_the_card_suits_that_will_be_used() {
        List<String> expected_suits = Arrays.asList("Spades", "Hearts", "Clubs", "Diamonds");

        for (String suit : expected_suits) {
            String actual_suit = page.getSuit(suit);
            assertEquals(suit, actual_suit);
        }
    }

    @When("I click on value {string}")
    public void i_click_on_value(String string) {
        page.clickValue(string);
    }

    @Then("value {string} is removed")
    public void value_is_removed(String string) {
        try {
            page.getValue(string);
            fail();
        } catch (NoSuchElementException e) {}
    }

    @When("I click on suit {string}")
    public void i_click_on_suit(String string) {
        page.clickSuit(string);
    }

    @Then("suit {string} is removed")
    public void suit_is_removed(String string) {
        try {
            page.getSuit(string);
            fail();
        } catch (NoSuchElementException e) {}
    }

    @When("I type in value {string}")
    public void i_type_in_value(String string) {
        page.typeValue(string);
    }

    @Then("Value {string} is added to the values display")
    public void value_is_added_to_the_values_display(String string) {
        String actual_value = page.getValue(string);
        assertEquals(string, actual_value);
    }

    @When("I press the add value button")
    public void i_press_the_add_button() {
        page.clickAddValue();
    }

    @When("I type in suit {string}")
    public void i_type_in_suit(String string) {
        page.typeSuit(string);
    }

    @Then("Suit {string} is added to the suits display")
    public void suit_is_added_to_the_suits_display(String string) {
        String actual_suit = page.getSuit(string);
        assertEquals(string, actual_suit);
    }

    @When("I press the add suit button")
    public void i_press_the_add_suit_button() {
        page.clickAddSuit();
    }

    @Then("I should see an error message in the config page")
    public void i_should_see_an_error_message() {
        assertFalse(page.getErrorMessage().equals(""));
    }

    @Then("I should see value {string}")
    public void i_should_see_value(String string) {
        assertNotNull(page.getValue(string));
    }

    @Then("I should see suit {string}")
    public void i_should_see_suit(String string) {
        assertNotNull(page.getSuit(string));
    }
}
