package nz.auckland.se754.a6.webapp.pages;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CardActivityConfigPage {
    private WebDriver _driver;

    public CardActivityConfigPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    @FindBy(how = How.ID, using="value-input")
    private WebElement valueInput;

    @FindBy(how = How.ID, using="suit-input")
    private WebElement suitInput;

    @FindBy(how = How.ID, using="value-submit")
    private WebElement valueSubmit;

    @FindBy(how = How.ID, using="suit-submit")
    private WebElement suitSubmit;

    @FindBy(how = How.ID, using="error-msg")
    private WebElement error;

    private WebElement valueElement(String value) {
        return this._driver.findElement(By.name("value_"+value));
    }

    private WebElement suitElement(String suit) {
        return this._driver.findElement(By.name("suit_"+suit));
    }

    public String getSuit(String suit) {
        return suitElement(suit).findElement(By.name("card_suit")).getAttribute("value");
    }

    public String getValue(String value) {
        return valueElement(value).findElement(By.name("card_value")).getAttribute("value");
    }

    public void clickAddValue() {
        valueSubmit.click();
    }

    public void clickAddSuit() {
        suitSubmit.click();
    }

    public void clickValue(String value) {
        valueElement(value).findElement(By.name("submit")).click();
    }

    public void clickSuit(String suit) {
        suitElement(suit).findElement(By.name("submit")).click();
    }

    public String getErrorMessage() {
        return error.getText();
    }

    public void typeValue(String value) {
        valueInput.sendKeys(value);
    }

    public void typeSuit(String suit) {
        suitInput.sendKeys(suit);
    }
}
