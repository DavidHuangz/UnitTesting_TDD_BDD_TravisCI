package nz.auckland.se754.a6.webapp.pages;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardActivityGamePage {
    private WebDriver _driver;

    public CardActivityGamePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    @FindBy(how = How.ID, using="player-input")
    private WebElement playerInput;

    @FindBy(how = How.ID, using="add-player")
    private WebElement addPlayer;

    @FindBy(how = How.ID, using="error-msg")
    private WebElement error;

    @FindBy(how = How.NAME, using="card_container")
    private WebElement cardContainer;

    @FindBy(how = How.NAME, using="play_game")
    private WebElement playGame;

    @FindBy(how = How.CLASS_NAME, using="activity-player")
    private WebElement activityPlayer;


    public String getErrorMessage() {
        return error.getText();
    }

    public void typePlayer(String string) {
        playerInput.sendKeys(string);
    }

    public void clickAddPlayer() {
        addPlayer.click();
    }

    public String getPlayer(String string) {
        return this._driver.findElement(By.name(string)).getText();
    }

    public void clickPlay() {
        playGame.click();
    }

    public WebElement getCard(String string) {
        return this._driver.findElement(By.name("card_"+string));
    }

    public String getCardValue(String string) {
        return getCard(string).findElement(By.className("activity-card")).getText();
    }

    public List<String> getAllCardPlayers() {
        List<WebElement> cards = cardContainer.findElements(By.className("card-wrapper"));

        List<String> players = new ArrayList<>();
        for (WebElement c : cards) {
            players.add(c.findElement(By.className("card-player")).getText());
        }

        return players;
    }

    public void clickPlayerCard(String string) {
        WebElement card = this._driver.findElement(By.name("card_"+string));
        card.findElement(By.name("select_player")).click();
    }
}
