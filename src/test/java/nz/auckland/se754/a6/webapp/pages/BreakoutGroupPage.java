package nz.auckland.se754.a6.webapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BreakoutGroupPage {
    WebDriver _driver;

    public BreakoutGroupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    @FindBy(how = How.NAME, using="parameters")
    private WebElement parameters;

    @FindBy(how = How.NAME, using="make_num_users")
    private WebElement numUsersSubmit;

    @FindBy(how = How.NAME, using="make_num_groups")
    private WebElement numGroupsSubmit;

    @FindBy(how = How.NAME, using="number_users")
    private WebElement numUsersInput;

    @FindBy(how = How.NAME, using="number_groups")
    private WebElement numGroupsInput;

    @FindBy(how = How.ID, using="random_users")
    private WebElement randomUsersSetting;

    @FindBy(how = How.ID, using="user_choice")
    private WebElement userChoiceSetting;

    @FindBy(how = How.NAME, using="make_groups")
    private WebElement makeGroupsBtn;

    @FindBy(how = How.ID, using="error-msg")
    private WebElement error;

    @FindBy(how = How.ID, using="breakout-groups-container")
    private WebElement breakoutGroups;

    @FindBy(how = How.ID, using="users-in-meeting")
    private WebElement usersInMeeting;

    @FindBy(how = How.ID, using="user-streams")
    private WebElement userStreams;

    @FindBy(how = How.NAME, using="disband_group")
    private WebElement disbandGroup;

    public void selectNumberOfUsersOption() {
        numUsersSubmit.click();
    }

    public boolean paramatersAreVisible() {
        try {
            return _driver.findElement(By.id("parameters")).isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void selectNumberOfGroupsOption() {
        numGroupsSubmit.click();
    }

    public void inputNumberOfUsers(Integer int1) {
        numUsersInput.sendKeys(String.valueOf(int1));
    }

    public String numberOfUsersValue() {
        return numUsersInput.getAttribute("value");
    }

    public String numberOfGroupsValue() {
        return numGroupsInput.getAttribute("value");
    }

    public void inputNumberOfGroups(Integer int1) {
        numGroupsInput.sendKeys(String.valueOf(int1));
    }

    public boolean userParametersAreVisible() {
        try {
            return _driver.findElement(By.name("user_params")).isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean groupParametersAreVisible() {
        try {
            return _driver.findElement(By.name("group_params")).isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean randomUserAllocationSetting() {
        return randomUsersSetting.isSelected();
    }

    public void selectRandomUserAllocation() {
        randomUsersSetting.click();
    }

    public void selectUserChoiceAllocation() {
        userChoiceSetting.click();
    }

    public boolean userChoiceAllocationSetting() {
        return userChoiceSetting.isSelected();
    }

    public void makeGroups() {
        makeGroupsBtn.click();
    }

    public Object getErrorMessage() {
        return error.getText();
    }

    public int numberOfDisplayedBreakoutGroups() {
        return breakoutGroups.findElements(By.className("breakout-group")).size();
    }

    public void addUsersToMeeting(Integer int1) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        js.executeScript("arguments[0].removeAttribute(\"readonly\")", usersInMeeting);
        js.executeScript("arguments[0].setAttribute(\"value\", arguments[1])", usersInMeeting, int1);
    }

    public boolean groupsHaveAtLeast(Integer int1) {
        List<WebElement> groups = breakoutGroups.findElements(By.className("breakout-group"));
        for (WebElement g : groups) {
            WebElement user_container = g.findElement(By.name("users"));
            String[] users = user_container.getText().split(",");
            if (users.length < int1) {
                return false;
            }
        }
        return true;
    }

    public boolean groupsHaveAtMost(Integer int1) {
        List<WebElement> groups = breakoutGroups.findElements(By.className("breakout-group"));
        for (WebElement g : groups) {
            WebElement user_container = g.findElement(By.name("users"));
            String[] users = user_container.getText().split(",");
            if (users.length > int1) {
                return false;
            }
        }
        return true;
    }

    public void joinGroup(Integer int1) {
        WebElement group = breakoutGroups.findElements(By.className("breakout-group")).get(int1);
        group.findElement(By.name("join_group")).click();
    }

    public int getNumberOfUserStreams() {
        return userStreams.findElements(By.className("user")).size();
    }

    public void disbandGroup() {
        disbandGroup.click();
    }
}
