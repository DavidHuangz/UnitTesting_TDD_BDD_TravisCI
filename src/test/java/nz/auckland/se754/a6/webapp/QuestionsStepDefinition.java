package nz.auckland.se754.a6.webapp;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nz.auckland.se754.a6.webapp.pages.QuestionsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionsStepDefinition {
    private WebDriver driver;
    private QuestionsPage questionsPage;

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
        questionsPage = new QuestionsPage(driver);
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

    @Given("I visit questions page")
    public void i_visit_questions_page() {
        driver.get("http://localhost:8080/questions" );
    }

    @When("I enter {string} as question field")
    public void i_enter_as_question_field(String string) {
        questionsPage.insertNewQuestion(string);
    }

    @When("I press the ask button")
    public void i_press_the_ask_button() {
        questionsPage.clickAsk();
    }

    @When("I press the answer button")
    public void i_press_the_answer_button() {
        questionsPage.clickAnswer();
    }

    @When("I select question {string} to upvote")
    public void i_select_question_to_upvote(String string) {
        new Select(driver.findElement(By.name("upvoteSelection"))).selectByVisibleText(string);
    }

    @When("I select question {string} to answer")
    public void i_select_question_to_answer(String string) {
        new Select(driver.findElement(By.name("questionSelection"))).selectByVisibleText(string);
    }

    @When("I select question {string} to delete")
    public void i_select_question_to_delete(String string) {
        new Select(driver.findElement(By.name("deleteSelection"))).selectByVisibleText(string);
    }

    @When("I select question {string} to mark")
    public void i_select_question_to_mark(String string) {
        new Select(driver.findElement(By.name("MarkSelection"))).selectByVisibleText(string);
    }

    @When("I enter {string} as answer field")
    public void i_enter_as_answer_field(String string) {
        questionsPage.insertNewAnswer(string);
    }

    @When("I sort question by {string}")
    public void i_sort_question_by(String string) {
        new Select(driver.findElement(By.name("sortSelection"))).selectByVisibleText(string);
    }

    @Then("I should see all questions sorted by time")
    public void i_should_see_all_questions_sorted_by_time() {
        assertTrue(questionsPage.getQuestionListTime().contains("Latest Questions"));
    }

    @Then("I should see all questions sorted by Most Voted Questions")
    public void i_should_see_all_questions_sorted_by_most_voted_questions() {
        assertTrue(questionsPage.getQuestionListUpvote().contains("Most Voted Questions"));
    }

    @Then("I should see all questions sorted by Answered Questions")
    public void i_should_see_all_questions_sorted_by_answered_questions() {
        assertTrue(questionsPage.getQuestionListAnswered().contains("Answered Questions"));
    }

    @Then("I should see my question")
    public void i_should_see_my_question() {
        assertTrue(questionsPage.getQuestionListTime().contains("How long is this meeting?"));
    }

    @Then("I should see question one with one more upvote")
    public void i_should_see_question_with_one_more_upvote() {
        assertTrue(questionsPage.getQuestionListTime().contains("Vote: 1"));
    }

    @Then("I should see my answer for question one")
    public void i_should_see_my_answer_for_question_one() {
        assertTrue(questionsPage.getQuestionListTime().contains("Reply: Go to science building"));
    }

    @Then("I should see question one removed")
    public void i_should_see_question_one_removed() {
        assertFalse(questionsPage.getQuestionListTime().contains("1. How to access this?"));
    }

    @Then("I should see question one as resolved to true")
    public void i_should_see_question_one_as_resolved_to_true() {
        assertTrue(questionsPage.getQuestionListAnswered().contains("Resolved: true"));
    }
}

