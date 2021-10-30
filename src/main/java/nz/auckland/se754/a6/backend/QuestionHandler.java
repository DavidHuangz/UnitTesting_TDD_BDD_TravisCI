package nz.auckland.se754.a6.backend;

import java.util.*;

public class QuestionHandler {
    private final List<QuestionsMeeting> questionList;
    private final List<QuestionsMeeting> answeredQuestions;
    public QuestionHandler() {
        questionList = new ArrayList<>();
        answeredQuestions = new ArrayList<>();
    }

    public void addQuestion(QuestionsMeeting newQuestion) {
        questionList.add(newQuestion);
    }

    public List<QuestionsMeeting> getList() {
        return questionList;
    }

    public List<QuestionsMeeting> getAnsweredQuestionsList() {
        return answeredQuestions;
    }

    public List<QuestionsMeeting> getQuestionUpvoteList() {
        List<QuestionsMeeting> questionUpvoteList = new ArrayList<>();
        sortByVotes(questionUpvoteList);
        return questionUpvoteList;
    }

    public void upVote(int QuestionNum) {
        questionList.get(QuestionNum - 1).setVote();
    }

    public QuestionsMeeting getQuestion(int QuestionNum) {
        return questionList.get(QuestionNum - 1);
    }

    private void sortByVotes(List<QuestionsMeeting> questionUpvoteList) {
        // Get questionUpvoteList
        questionUpvoteList.addAll(questionList);
        // sort through upvote using BubbleSort method
        for (int i = 0; i < questionUpvoteList.size() - 1; i++)
            for (int j = 0; j < questionUpvoteList.size() - i - 1; j++)
                if (questionUpvoteList.get(j).getVote() < questionUpvoteList.get(j + 1).getVote()) {
                    // swap
                    QuestionsMeeting temp = questionUpvoteList.get(j);
                    questionUpvoteList.set(j, questionUpvoteList.get(j + 1));
                    questionUpvoteList.set(j + 1, temp);
                }
    }

    public void removeQuestion(int questionNum) {
        questionList.remove(questionNum - 1);
    }

    public void setQuestionAnswer(int QuestionNum, String newAnswer) {
        QuestionsMeeting q = questionList.get(QuestionNum - 1);
        q.setAnswer(newAnswer);
        answeredQuestions.add(q);
    }

    public void markedAsResolved(int QuestionNum) {
        QuestionsMeeting q = questionList.get(QuestionNum - 1);
        q.setResolved();
        answeredQuestions.add(q);
        questionList.remove(QuestionNum - 1);
    }
}