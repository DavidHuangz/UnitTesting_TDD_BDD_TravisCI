package nz.auckland.se754.a6.backend;

public class QuestionsMeeting {
    String question;
    String answer;
    Boolean resolved;
    int vote;

    public QuestionsMeeting(String newQuestion) {
        question = newQuestion;
        answer = "";
        vote = 0;
        resolved = false;
    }

    public Boolean getResolved() { return resolved; }

    public void setResolved() { resolved = true; }

    public String getQuestions() { return question; }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String newAnswer) { answer = newAnswer; }

    public int getVote() {
        return vote;
    }

    public void setVote() {
        vote++;
    }
}
