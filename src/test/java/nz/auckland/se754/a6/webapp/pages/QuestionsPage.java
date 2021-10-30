package nz.auckland.se754.a6.webapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class QuestionsPage {

    public QuestionsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "questionListTime")
    private WebElement questionListTime;

    @FindBy(how = How.ID, using = "questionListUpvote")
    private WebElement questionListUpvote;

    @FindBy(how = How.ID, using = "questionListAnswered")
    private WebElement questionListAnswered;

    @FindBy(how = How.ID, using = "askQuestion")
    private WebElement askQuestion;

    @FindBy(how = How.ID, using = "answerQuestion")
    private WebElement answerQuestion;

    @FindBy(how = How.ID, using = "askBtn")
    private WebElement askBtn;

    @FindBy(how = How.ID, using = "answerBtn")
    private WebElement answerBtn;

    public String getQuestionListTime() {
        return this.questionListTime.getText();
    }

    public String getQuestionListUpvote() {
        return this.questionListUpvote.getText();
    }

    public String getQuestionListAnswered() {
        return this.questionListAnswered.getText();
    }

    public void insertNewQuestion(String askQuestion) { this.askQuestion.sendKeys(askQuestion); }

    public void insertNewAnswer(String answerQuestion) {
        this.answerQuestion.sendKeys(answerQuestion);
    }

    public void clickAsk() {
        this.askBtn.click();
    }

    public void clickAnswer() {
        this.answerBtn.click();
    }
}