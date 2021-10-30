package nz.auckland.se754.a6.controller;

import nz.auckland.se754.a6.backend.QuestionHandler;
import nz.auckland.se754.a6.backend.QuestionsMeeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("questions")
public class QuestionController {
    private final QuestionsMeeting qm1;
    private final QuestionsMeeting qm2;
    private final QuestionHandler qh;
    private boolean temp;

    public QuestionController() {
        qm1 = new QuestionsMeeting("How to access this?");
        qm2 = new QuestionsMeeting("When is the next meeting?");
        qh = new QuestionHandler();
        temp = true;
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public String showQuestionsPage(ModelMap model) {
        if (temp) {
            qh.addQuestion(qm1);
            qh.addQuestion(qm2);
            qh.upVote(2);
            qh.upVote(2);
            temp = false;
        }
        model.put("List", qh.getList());
        model.put("AnsweredList", qh.getAnsweredQuestionsList());
        model.put("qh", qh);
        return "questions";
    }

    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public String showQuestions(ModelMap model, @RequestParam String askQuestion,
                                @RequestParam String answerQuestion, @RequestParam String questionSelection,
                                @RequestParam String deleteSelection, @RequestParam String MarkSelection,
                                @RequestParam String sortSelection, @RequestParam String upvoteSelection) {

        // If Delete question
        if (!deleteSelection.equals("")) {
            qh.removeQuestion(Integer.parseInt(deleteSelection));
        }

        // If mark question
        else if (!MarkSelection.equals("")) {
            qh.markedAsResolved(Integer.parseInt(MarkSelection));
        }

        // If upvote button pressed
        else if (!upvoteSelection.equals("")) {
            qh.upVote(Integer.parseInt(upvoteSelection));
        }

        // if ask question and answer question not entered field, display error message
        else if (askQuestion.equals("") && answerQuestion.equals("") && sortSelection.equals("")) {
            model.put("errorMessage", "You have not entered a text");
        }

        // If ask questions button pressed
        else if (!askQuestion.equals("")) {
            QuestionsMeeting userAsk = new QuestionsMeeting(askQuestion);
            qh.addQuestion(userAsk);
        }

        // If answer questions button pressed
        else if (qh.getList().size() != 0 && !questionSelection.equals("")) {
            qh.setQuestionAnswer(Integer.parseInt(questionSelection), answerQuestion);

            // if user have not selected a question to answer, display error message
        } else if (questionSelection.equals("") && sortSelection.equals("")){
            model.put("errorMessage2", "Please select a question");
        }

        model.put("List", qh.getList());
        model.put("AnsweredList", qh.getAnsweredQuestionsList());
        model.put("qh", qh);
        return "questions";
    }
}