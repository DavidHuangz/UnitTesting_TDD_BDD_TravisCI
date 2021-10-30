package nz.auckland.se754.a6.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "name")
    private WebElement userName;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.ID, using = "newPassword")
    private WebElement newPassword;

    @FindBy(how = How.ID, using = "submitbtn")
    private WebElement submitButton;

    @FindBy(how = How.ID, using = "HomePageMessage")
    private WebElement HomePageMessage;

    @FindBy(how = How.ID, using = "error")
    private WebElement error;

    @FindBy(how = How.ID, using = "resetPassBtn")
    private WebElement resetPassBtn;

    @FindBy(how = How.ID, using = "logoutBtn")
    private WebElement logoutBtn;

    public void insertUserName(String userName) {
        this.userName.sendKeys(userName);
    }

    public void insertPassword(String password) {
        this.password.sendKeys(password);
    }

    public void insertNewPassword(String newPassword) {
        this.newPassword.sendKeys(newPassword);
    }

    public void clickLogin() {
        this.submitButton.click();
    }

    public void clickLogout() { this.logoutBtn.click(); }

    public void clickReset() {
        this.resetPassBtn.click();
    }

    public String getHomePageMessage() {
        return this.HomePageMessage.getText();
    }

    public String getError() {
        return this.error.getText();
    }
}