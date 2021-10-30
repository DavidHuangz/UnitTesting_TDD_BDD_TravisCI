package nz.auckland.se754.a6.webapp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.auckland.se754.a6.webapp.pages.CardActivityGamePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CardActivityGameStepDefinitions {

    private WebDriver driver;
    private CardActivityGamePage page;
    List<String> card_store;

    @Before
    public void setup() {
        if (System.getProperty("os.name").startsWith("Windows")){
            System.setProperty("webdriver.chrome.driver", "webdrivers/win/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "webdrivers/macos/chromedriver");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        page = new CardActivityGamePage(driver);
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

    @Given("I have gone to the play game page")
    public void i_have_gone_to_the_play_game_page() {
        driver.get("http://localhost:8080/card-activity/play");
    }

    @When("I type {string} into the player box")
    public void i_type_into_the_player_box(String string) {
        page.typePlayer(string);
    }

    @When("I click the add player button")
    public void i_click_the_add_player_button() {
        page.clickAddPlayer();
    }

    @Then("{string} appears in the player list")
    public void appears_in_the_player_list(String string) {
        assertEquals(string, page.getPlayer(string));
    }

    @When("I click the play button")
    public void i_click_the_play_button() {
        page.clickPlay();
    }

    @Then("I can see a card for {string}")
    public void i_can_see_a_card_for(String string) {
        assertNotNull(page.getCard(string));
    }

    @Then("The card for {string} has a card value")
    public void the_card_for_has_a_card_value(String string) {
        assertFalse(page.getCardValue(string).equals(""));
    }

    @When("I click player {string} card")
    public void i_click_player_card(String string) {
        card_store = page.getAllCardPlayers();
        page.clickPlayerCard(string);
    }

    @Then("{string} card and {string} card are swapped")
    public void card_and_card_are_swapped(String string, String string2) {
        List<String> current_cards = page.getAllCardPlayers();

        assertEquals(card_store.indexOf(string), current_cards.indexOf(string2));
        assertEquals(card_store.indexOf(string2), current_cards.indexOf(string));
    }

    @Then("I should see an error message in the play page")
    public void i_should_see_an_error_message() {
        assertFalse(page.getErrorMessage().equals(""));
    }
}
