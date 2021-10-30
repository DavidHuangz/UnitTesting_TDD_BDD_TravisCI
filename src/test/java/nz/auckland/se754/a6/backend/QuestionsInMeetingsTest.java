package nz.auckland.se754.a6.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionsInMeetingsTest {

    private QuestionHandler qh;

    @BeforeEach
    void setup() {
        QuestionsMeeting qm1 = new QuestionsMeeting("How to access this?");
        QuestionsMeeting qm2 = new QuestionsMeeting("When is the next meeting?");
        QuestionsMeeting qm3 = new QuestionsMeeting("Can we extend the due date please?");
        qh = new QuestionHandler();
        qh.addQuestion(qm1);
        qh.addQuestion(qm2);
        qh.addQuestion(qm3);
    }

    @Test
    @Tag("UnitTest")
    public void userAskingQuestionsDuringMeetings() {
        QuestionsMeeting qm1 = new QuestionsMeeting("How to access this?");
        qh = new QuestionHandler();
        qh.addQuestion(qm1);
        assertTrue(qh.getList().contains(qm1));
    }

    @Test
    @Tag("UnitTest")
    public void userViewAllQuestionsInTime() {
        assertEquals(qh.getList().get(0).getQuestions(), "How to access this?");
        assertEquals(qh.getList().get(1).getQuestions(), "When is the next meeting?");
        assertEquals(qh.getList().get(2).getQuestions(), "Can we extend the due date please?");
    }

    @Test
    @Tag("UnitTest")
    public void userViewAllAnsweredQuestionsInTime() {
        qh.setQuestionAnswer(1, "Go to reception for key.");
        qh.setQuestionAnswer(2, "Next week thursday.");
        assertEquals(qh.getAnsweredQuestionsList().get(0).getQuestions(), "How to access this?");
        assertEquals(qh.getAnsweredQuestionsList().get(1).getQuestions(), "When is the next meeting?");
    }

    @Test
    @Tag("UnitTest")
    public void userUpvoteQuestion() {
        qh.upVote(1);
        qh.upVote(1);
        assertEquals(2, qh.getQuestion(1).getVote());
    }

    @Test
    @Tag("UnitTest")
    public void userAnswerQuestion() {
        qh.setQuestionAnswer(1, "Go to reception for key.");
        qh.setQuestionAnswer(2, "Next week thursday.");
        qh.setQuestionAnswer(3, "Nope.");
        assertEquals(qh.getAnsweredQuestionsList().get(0).getAnswer(), "Go to reception for key.");
        assertEquals(qh.getAnsweredQuestionsList().get(1).getAnswer(), "Next week thursday.");
        assertEquals(qh.getAnsweredQuestionsList().get(2).getAnswer(), "Nope.");
    }

    @Test
    @Tag("UnitTest")
    public void organiserCanRemoveQuestions() {
        qh.removeQuestion(1);
        assertEquals(qh.getList().get(0).getQuestions(), "When is the next meeting?");
        assertEquals(qh.getList().get(1).getQuestions(), "Can we extend the due date please?");
    }

    @Test
    @Tag("UnitTest")

    public void organiserMarkQuestionAsResolved() {
        qh.setQuestionAnswer(1, "Go to reception for key.");
        qh.markedAsResolved(1);
        assertTrue(qh.getAnsweredQuestionsList().get(0).getResolved());
    }

    @Test
    @Tag("UnitTest")
    public void userViewAllAnsweredQuestionsInUpvote() {
        qh.upVote(3);
        qh.upVote(3);
        qh.upVote(3);
        qh.upVote(1);
        qh.upVote(1);
         List<QuestionsMeeting> upVoteQuestions = qh.getQuestionUpvoteList();
        assertEquals(upVoteQuestions.get(0).getQuestions(), "Can we extend the due date please?");
        assertEquals(upVoteQuestions.get(1).getQuestions(), "How to access this?");
        assertEquals(upVoteQuestions.get(2).getQuestions(), "When is the next meeting?");
    }
}
