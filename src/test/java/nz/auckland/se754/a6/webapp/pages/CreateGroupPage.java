package nz.auckland.se754.a6.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Keys;

public class CreateGroupPage {

    public CreateGroupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how=How.ID, using ="groupName")
    private WebElement groupName;

    @FindBy(how=How.ID, using ="name")
    private WebElement userName;

    @FindBy(how = How.ID, using = "createbtn")
    private WebElement createButton;

    @FindBy(how = How.ID, using = "addbtn")
    private WebElement addButton;

    @FindBy(how = How.ID, using = "removebtn")
    private WebElement removeButton;

    @FindBy(how=How.ID, using ="message")
    private WebElement message;

    public void insertGroupName(String groupName) {
        this.groupName.sendKeys(groupName);
    }
    public void insertUserName(String userName) {
        this.userName.sendKeys(userName);
    }
    public void clickCreate(){
        this.createButton.click();
    }
    public void clickAdd() {this.addButton.click();}
    public void clickRemove() {this.removeButton.click();}
    public String getMessage(){
        return this.message.getText();
    }
}
