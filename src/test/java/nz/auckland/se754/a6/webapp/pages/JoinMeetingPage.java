package nz.auckland.se754.a6.webapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class JoinMeetingPage {

    private WebDriver _driver;

    public JoinMeetingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    @FindBy(how= How.ID, using ="meetingID")
    private WebElement meetingID;

    @FindBy(how = How.ID, using = "JoinBtn")
    private WebElement JoinBtn;

    @FindBy(how=How.ID, using ="message")
    private WebElement message;

    @FindBy(how = How.CLASS_NAME, using="meetingId")
    private WebElement meetingId;

    @FindBy(how = How.CLASS_NAME, using="meetingTitle")
    private WebElement meetingTitle;

    @FindBy(how = How.CLASS_NAME, using="currentMeeting")
    private WebElement currentMeeting;

    public List<String> getMeetingId() {
        List<WebElement> Id = currentMeeting.findElements(By.className("meetingId"));

        List<String> meetingId = new ArrayList<>();
        for (WebElement id : Id) {
            meetingId.add(id.getText());
        }

        return meetingId;
    }

    public List<String> getMeetingTitle() {
        List<WebElement> titles = currentMeeting.findElements(By.className("meetingTitle"));

        List<String> meetingTitle = new ArrayList<>();
        for (WebElement title : titles) {
            meetingTitle.add(title.getText());
        }

        return meetingTitle;
    }

    public void insertMeetingID(String meetingID) {
        this.meetingID.sendKeys(meetingID);
    }
    public void clickJoin() {
        this.JoinBtn.click();
    }
    public String getMessage(){
        return this.message.getText();
    }
}
